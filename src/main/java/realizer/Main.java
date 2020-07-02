package realizer;

import classifiers.Adjectives;
import classifiers.Nouns;
import classifiers.Verbs;
import gate.*;
import gate.util.GateException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.EnumSet;

/**
 * Created by Ahmed on 1/28/16.
 */
public class Main {
    public static void main(String[] args) {
        //lunching gate
        try {
            Gate.init();

            PrintWriter dumpfile = new PrintWriter("dumpfile.txt", "UTF-8");
            PrintWriter dumpfileMissingFeatures = new PrintWriter("dumpfileMissingFeatures.txt", "UTF-8");

            Document doc;
            String floc;
            AnnotationSet markups;
            AnnotationSet tokens;
            FeatureMap fm;

            File inDir = new File("ParsedDocs");

            File[] flist = inDir.listFiles();
            String fname;

            Realizer realizer = new Realizer();

            //setting resources if not set default values will be used
            //setting Transitions
            realizer.setNounsTransitionsMapFileName("NMap");
            realizer.setVerbsTransitionsMapFileName("VMap");
            realizer.setAdjectivesTransitionsMapFileName("AMap");
            //setting Models
            realizer.setNounsModelFileName("J48Nouns");
            realizer.setVerbsModelFileName("J48Verbs");
            realizer.setAdjectivesModelFileName("J48Adjectives");
            //setting FeaturesSets
            realizer.setNounsFeaturesSet(EnumSet.allOf(Nouns.Features.class));
            realizer.setVerbsFeaturesSet(EnumSet.allOf(Verbs.Features.class));
            realizer.setAdjectivesFeaturesSet(EnumSet.allOf(Adjectives.Features.class));

            //initializing the realizer
            realizer.initializeRealizer();

            String generated;
            for (File file : flist) {
                floc = file.getAbsolutePath();
                doc = Factory.newDocument(new URL("file:///" + floc), "UTF-8");
                markups = doc.getAnnotations("");
                tokens = markups.get("Token");

                for (Annotation token : tokens) {
                    String tcat = (String) token.getFeatures().get("category");
                    String tlemma = (String) token.getFeatures().get("lemma");
                    String tform = (String) token.getFeatures().get("string");

                    tlemma = tlemma.toLowerCase();
                    tform = tform.toLowerCase();

                    String features = (String) token.getFeatures().get("pfeatures");

                    generated = realizer.realizeToken(token);

                    if (generated != null) {

                        if (tcat.equals("n") && !tlemma.equals(tform)) {
                            if (!features.contains("gen") || !features.contains("num")) {
                                System.out.print("Missing Features -> ");
                                dumpfile.print("Missing Features -> ");

                                dumpfileMissingFeatures.print("Missing Features -> ");
                                dumpfileMissingFeatures.print("noun: lemma " + tlemma + " ourform " + generated
                                        + " originalform " + tform);
                                if (tform.equals(generated)) {
                                    dumpfileMissingFeatures.println("\t" + "ok");
                                } else {
                                    dumpfileMissingFeatures.println("\t" + "**");
                                }
                            }

                            System.out.print("noun: lemma " + tlemma + " ourform " + generated
                                    + " originalform " + tform);

                            dumpfile.print("noun: lemma " + tlemma + " ourform " + generated
                                    + " originalform " + tform);

                            if (tform.equals(generated)) {
                                System.out.println("\t" + "ok");
                                dumpfile.println("\t" + "ok");
                            } else {
                                System.out.println("\t" + "**");
                                dumpfile.println("\t" + "**");
                            }
                        } else if (tcat.equals("v") && !tlemma.equals(tform)) {
                            if (!features.contains("person") || !features.contains("num") ||
                                    !features.contains("tense") || !features.contains("mood")) {
                                System.out.print("Missing Features -> ");
                                dumpfile.print("Missing Features -> ");

                                dumpfileMissingFeatures.print("Missing Features -> ");
                                dumpfileMissingFeatures.print("verb: lemma " + tlemma + " ourform " + generated
                                        + " originalform " + tform);
                                if (tform.equals(generated)) {
                                    dumpfileMissingFeatures.println("\t" + "ok");
                                } else {
                                    dumpfileMissingFeatures.println("\t" + "**");
                                }
                            }

                            System.out.print("verb: lemma " + tlemma + " ourform " + generated +
                                    " originalform " + tform);

                            dumpfile.print("verb: lemma " + tlemma + " ourform " + generated +
                                    " originalform " + tform);

                            if (tform.equals(generated)) {
                                System.out.println("\t" + "ok");
                                dumpfile.println("\t" + "ok");
                            } else {
                                System.out.println("\t" + "**");
                                dumpfile.println("\t" + "**");
                            }

                        }
                        else if (tcat.equals("a") && !tlemma.equals(tform)) {
                            if (!features.contains("gen") || !features.contains("num")) {
                                System.out.print("Missing Features -> ");
                                dumpfile.print("Missing Features -> ");

                                dumpfileMissingFeatures.print("Missing Features -> ");
                                dumpfileMissingFeatures.print("noun: lemma " + tlemma + " ourform " + generated
                                        + " originalform " + tform);
                                if (tform.equals(generated)) {
                                    dumpfileMissingFeatures.println("\t" + "ok");
                                } else {
                                    dumpfileMissingFeatures.println("\t" + "**");
                                }
                            }

                            System.out.print("adjective: lemma " + tlemma + " ourform " + generated
                                    + " originalform " + tform);

                            dumpfile.print("adjective: lemma " + tlemma + " ourform " + generated
                                    + " originalform " + tform);

                            if (tform.equals(generated)) {
                                System.out.println("\t" + "ok");
                                dumpfile.println("\t" + "ok");
                            } else {
                                System.out.println("\t" + "**");
                                dumpfile.println("\t" + "**");
                            }
                        }
                    }
                }
            }

            dumpfile.close();
            dumpfileMissingFeatures.close();
        } catch (GateException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
