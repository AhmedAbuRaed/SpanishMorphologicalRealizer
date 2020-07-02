package realizer;
/**
 * Created by Ahmed on 1/2/16.
 */

import classifiers.Adjectives;
import classifiers.Nouns;
import classifiers.Utilities;
import classifiers.Verbs;
import gate.*;
import gate.util.GateException;
import weka.classifiers.Classifier;
import weka.classifiers.misc.InputMappedClassifier;
import weka.core.Instances;

import java.io.File;
import java.util.*;

public class Realizer {
    static HashMap<String, HashMap<String, List<List<String>>>> irrVerb;
    HashMap<Integer, String> Nmap;
    HashMap<Integer, String> Vmap;
    HashMap<Integer, String> Amap;

    Verbs irrverbs;

    Instances ntestData;
    Instances vtestData;
    Instances atestData;

    int ntestDataSize;
    int vtestDataSize;
    int atestDataSize;

    Classifier nClassifier;
    Classifier vClassifier;
    Classifier aClassifier;

    InputMappedClassifier ninputMappedClassifier;
    InputMappedClassifier vinputMappedClassifier;
    InputMappedClassifier ainputMappedClassifier;

    HashMap<Nouns.Features, String> nounsComputedFeatures;
    HashMap<Verbs.Features, String> verbsComputedFeatures;
    HashMap<Adjectives.Features, String> adjectivesComputedFeatures;

    private String nounsTransitionsMapFileName;
    private String verbsTransitionsMapFileName;
    private String adjectivesTransitionsMapFileName;

    private String nounsModelFileName;
    private String verbsModelFileName;
    private String adjectivesModelFileName;

    private Set<Nouns.Features> nounsFeaturesSet;
    private Set<Verbs.Features> verbsFeaturesSet;
    private Set<Adjectives.Features> adjectivesFeaturesSet;

    public Realizer() {
        //Initialize Mapped Transitions
        Nmap = new HashMap<Integer, String>();
        Vmap = new HashMap<Integer, String>();
        Amap = new HashMap<Integer, String>();

        //Initialize Irreguler Verbs
        irrverbs = new Verbs();
        irrVerb = new HashMap<String, HashMap<String, List<List<String>>>>();

        ntestData = null;
        vtestData = null;
        atestData = null;

        //initialize the InputMappedClassifiers
        ninputMappedClassifier = new InputMappedClassifier();
        vinputMappedClassifier = new InputMappedClassifier();
        ainputMappedClassifier = new InputMappedClassifier();

        nounsComputedFeatures = new HashMap<Nouns.Features, String>();
        verbsComputedFeatures = new HashMap<Verbs.Features, String>();
        adjectivesComputedFeatures = new HashMap<Adjectives.Features, String>();

        setNounsTransitionsMapFileName("NMap");
        setVerbsTransitionsMapFileName("VMap");
        setAdjectivesTransitionsMapFileName("AMap");

        setNounsModelFileName("J48Nouns");
        setVerbsModelFileName("J48Verbs");
        setAdjectivesModelFileName("J48Adjectives");

        setNounsFeaturesSet(EnumSet.allOf(Nouns.Features.class));
        setVerbsFeaturesSet(EnumSet.allOf(Verbs.Features.class));
        setAdjectivesFeaturesSet(EnumSet.allOf(Adjectives.Features.class));
    }

    public void initializeRealizer() {
        try {
            //loading transitions hashmap
            Nmap = Utilities.loadTransitions(getNounsTransitionsMapFileName());
            Vmap = Utilities.loadTransitions(getVerbsTransitionsMapFileName());
            Amap = Utilities.loadTransitions(getAdjectivesTransitionsMapFileName());
            System.out.println("Transitions loaded");

            //loading irregular verbs
            irrVerb = irrverbs.loadIrrVerbs();
            System.out.println("Irregular verbs loaded");

            //load test data attributes
            ntestData = Utilities.loadTestData(getNounsModelFileName());
            vtestData = Utilities.loadTestData(getVerbsModelFileName());
            atestData = Utilities.loadTestData(getAdjectivesModelFileName());
            System.out.println("Test Attributes loaded");

            //setting up serializedclassifer
            nClassifier = Utilities.loadClassifier(getNounsModelFileName());
            vClassifier = Utilities.loadClassifier(getVerbsModelFileName());
            aClassifier = Utilities.loadClassifier(getAdjectivesModelFileName());
            System.out.println("Models loaded");

            //setting up the InputMappedClassifier
            ninputMappedClassifier = Utilities.loadInputMappedClassifier(getNounsModelFileName());
            vinputMappedClassifier = Utilities.loadInputMappedClassifier(getVerbsModelFileName());
            ainputMappedClassifier = Utilities.loadInputMappedClassifier(getAdjectivesModelFileName());
            System.out.println("Input Mapped Classifiers loaded");
        } catch (GateException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String realizeToken(Annotation token) {
        String form = null;
        if (ntestData != null && vtestData != null && atestData != null) {
            try {
                FeatureMap fm;
                fm = token.getFeatures();

                if (fm.get("category") != null && (String) fm.get("lemma") != null && fm.get("pfeatures") != null) {
                    String category = (String) fm.get("category");
                    String lemma = (String) fm.get("lemma");
                    String extra = (String) fm.get("pfeatures");

                    lemma = lemma.toLowerCase();
                    form = lemma;

                    if (category.equals("n")) {
                        ntestDataSize = 0;
                        Nouns noun = new Nouns();
                        nounsComputedFeatures = Nouns.computeFeatures(getNounsFeaturesSet(), lemma, extra);
                        ntestData = noun.loadTestInstance(0, nounsComputedFeatures, Nouns.generateNounsAttributes());
                        if (ntestData.size() == ntestDataSize + 1) {
                            Instances ntestDataNominalToString = Utilities.applyNominalToStringVectorFilter(ntestData,
                                    "-C 19-24");
                            Instances ntestDataLemmaUniGramString = Utilities.applyStringToWordVectorFilter(ntestDataNominalToString,
                                    "-R 19 -P lu_ -W 1000 -prune-rate -1.0 -N 0 -stemmer weka.core.stemmers.NullStemmer -stopwords-handler weka.core.stopwords.Null -M 1 -tokenizer \"weka.core.tokenizers.WordTokenizer -delimiters \\\" \\\\r\\\\n\\\\t.,;:\\\\\\'\\\\\\\"()?!\\\"\"");
                            Instances ntestDataLemmaBiGramString = Utilities.applyStringToWordVectorFilter(ntestDataLemmaUniGramString,
                                    "-R 19 -P lb_ -W 1000 -prune-rate -1.0 -N 0 -stemmer weka.core.stemmers.NullStemmer -stopwords-handler weka.core.stopwords.Null -M 1 -tokenizer \"weka.core.tokenizers.WordTokenizer -delimiters \\\" \\\\r\\\\n\\\\t.,;:\\\\\\'\\\\\\\"()?!\\\"\"");
                            Instances ntestDataLemmaUniGramVCString = Utilities.applyStringToWordVectorFilter(ntestDataLemmaBiGramString,
                                    "-R 19 -P luvc_ -W 1000 -prune-rate -1.0 -N 0 -stemmer weka.core.stemmers.NullStemmer -stopwords-handler weka.core.stopwords.Null -M 1 -tokenizer \"weka.core.tokenizers.WordTokenizer -delimiters \\\" \\\\r\\\\n\\\\t.,;:\\\\\\'\\\\\\\"()?!\\\"\"");
                            Instances ntestDataLemmaBiGramVCString = Utilities.applyStringToWordVectorFilter(ntestDataLemmaUniGramVCString,
                                    "-R 19 -P lbvc_ -W 1000 -prune-rate -1.0 -N 0 -stemmer weka.core.stemmers.NullStemmer -stopwords-handler weka.core.stopwords.Null -M 1 -tokenizer \"weka.core.tokenizers.WordTokenizer -delimiters \\\" \\\\r\\\\n\\\\t.,;:\\\\\\'\\\\\\\"()?!\\\"\"");
                            Instances ntestDataLemmaUniGramOrderString = Utilities.applyStringToWordVectorFilter(ntestDataLemmaBiGramVCString,
                                    "-R 19 -P luo_ -W 1000 -prune-rate -1.0 -N 0 -stemmer weka.core.stemmers.NullStemmer -stopwords-handler weka.core.stopwords.Null -M 1 -tokenizer \"weka.core.tokenizers.WordTokenizer -delimiters \\\" \\\\r\\\\n\\\\t.,;:\\\\\\'\\\\\\\"()?!\\\"\"");
                            Instances ntestDataLemmaUniGramReverseOrderString = Utilities.applyStringToWordVectorFilter(ntestDataLemmaUniGramOrderString,
                                    "-R 19 -P luro_ -W 1000 -prune-rate -1.0 -N 0 -stemmer weka.core.stemmers.NullStemmer -stopwords-handler weka.core.stopwords.Null -M 1 -tokenizer \"weka.core.tokenizers.WordTokenizer -delimiters \\\" \\\\r\\\\n\\\\t.,;:\\\\\\'\\\\\\\"()?!\\\"\"");
                            Instances ntestDataFinal = Utilities.applyReorderFilter(ntestDataLemmaUniGramReverseOrderString, "-R first-18,20-last,19");
                            form = Utilities.classifyInstance(ntestDataFinal, 0, lemma, ninputMappedClassifier, Nmap);
                        }
                    } else if (category.equals("v")) {
                        if (!irrVerb.containsKey(lemma.toUpperCase())) {
                            vtestDataSize = 0;
                            Verbs verb = new Verbs();
                            verbsComputedFeatures = Verbs.computeFeatures(getVerbsFeaturesSet(), lemma, extra);
                            vtestData = verb.loadTestInstance(0, verbsComputedFeatures, Verbs.generateVerbsAttributes());
                            if (vtestData.size() == vtestDataSize + 1) {
                                Instances vtestDataNominalToString = Utilities.applyNominalToStringVectorFilter(vtestData,
                                        "-C 21-26");
                                Instances vtestDataLemmaUniGramString = Utilities.applyStringToWordVectorFilter(vtestDataNominalToString,
                                        "-R 21 -P lu_ -W 1000 -prune-rate -1.0 -N 0 -stemmer weka.core.stemmers.NullStemmer -stopwords-handler weka.core.stopwords.Null -M 1 -tokenizer \"weka.core.tokenizers.WordTokenizer -delimiters \\\" \\\\r\\\\n\\\\t.,;:\\\\\\'\\\\\\\"()?!\\\"\"");
                                Instances vtestDataLemmaBiGramString = Utilities.applyStringToWordVectorFilter(vtestDataLemmaUniGramString,
                                        "-R 21 -P lb_ -W 1000 -prune-rate -1.0 -N 0 -stemmer weka.core.stemmers.NullStemmer -stopwords-handler weka.core.stopwords.Null -M 1 -tokenizer \"weka.core.tokenizers.WordTokenizer -delimiters \\\" \\\\r\\\\n\\\\t.,;:\\\\\\'\\\\\\\"()?!\\\"\"");
                                Instances vtestDataLemmaUniGramVCString = Utilities.applyStringToWordVectorFilter(vtestDataLemmaBiGramString,
                                        "-R 21 -P luvc_ -W 1000 -prune-rate -1.0 -N 0 -stemmer weka.core.stemmers.NullStemmer -stopwords-handler weka.core.stopwords.Null -M 1 -tokenizer \"weka.core.tokenizers.WordTokenizer -delimiters \\\" \\\\r\\\\n\\\\t.,;:\\\\\\'\\\\\\\"()?!\\\"\"");
                                Instances vtestDataLemmaBiGramVCString = Utilities.applyStringToWordVectorFilter(vtestDataLemmaUniGramVCString,
                                        "-R 21 -P lbvc_ -W 1000 -prune-rate -1.0 -N 0 -stemmer weka.core.stemmers.NullStemmer -stopwords-handler weka.core.stopwords.Null -M 1 -tokenizer \"weka.core.tokenizers.WordTokenizer -delimiters \\\" \\\\r\\\\n\\\\t.,;:\\\\\\'\\\\\\\"()?!\\\"\"");
                                Instances vtestDataLemmaUniGramOrderString = Utilities.applyStringToWordVectorFilter(vtestDataLemmaBiGramVCString,
                                        "-R 21 -P luo_ -W 1000 -prune-rate -1.0 -N 0 -stemmer weka.core.stemmers.NullStemmer -stopwords-handler weka.core.stopwords.Null -M 1 -tokenizer \"weka.core.tokenizers.WordTokenizer -delimiters \\\" \\\\r\\\\n\\\\t.,;:\\\\\\'\\\\\\\"()?!\\\"\"");
                                Instances vtestDataLemmaUniGramReverseOrderString = Utilities.applyStringToWordVectorFilter(vtestDataLemmaUniGramOrderString,
                                        "-R 21 -P luro_ -W 1000 -prune-rate -1.0 -N 0 -stemmer weka.core.stemmers.NullStemmer -stopwords-handler weka.core.stopwords.Null -M 1 -tokenizer \"weka.core.tokenizers.WordTokenizer -delimiters \\\" \\\\r\\\\n\\\\t.,;:\\\\\\'\\\\\\\"()?!\\\"\"");
                                Instances vtestDataFinal = Utilities.applyReorderFilter(vtestDataLemmaUniGramReverseOrderString, "-R first-20,22-last,21");
                                form = Utilities.classifyInstance(vtestDataFinal, 0, lemma, vinputMappedClassifier, Vmap);
                            }
                        } else {
                            String person, number, tense, mood = null;
                            person = Verbs.computePerson(extra);
                            number = Verbs.computeNumber(extra);
                            tense = Verbs.computeTense(extra);
                            mood = Verbs.computeMood(extra);

                            if (lemma != null && mood != null && tense != null && number != null && person != null) {
                                Verbs irrVerbForm = new Verbs();
                                form = irrVerbForm.getIrrVerbForm(irrVerb, lemma, mood, tense, number, person);
                            }
                        }
                    } else if (category.equals("a")) {
                        atestDataSize = 0;
                        Adjectives adjective = new Adjectives();
                        adjectivesComputedFeatures = Adjectives.computeFeatures(getAdjectivesFeaturesSet(), lemma, extra);
                        atestData = adjective.loadTestInstance(0, adjectivesComputedFeatures, Adjectives.generateAdjectivesAttributes());
                        if (atestData.size() == atestDataSize + 1) {
                            Instances atestDataNominalToString = Utilities.applyNominalToStringVectorFilter(atestData,
                                    "-C 19-24");
                            Instances atestDataLemmaUniGramString = Utilities.applyStringToWordVectorFilter(atestDataNominalToString,
                                    "-R 19 -P lu_ -W 1000 -prune-rate -1.0 -N 0 -stemmer weka.core.stemmers.NullStemmer -stopwords-handler weka.core.stopwords.Null -M 1 -tokenizer \"weka.core.tokenizers.WordTokenizer -delimiters \\\" \\\\r\\\\n\\\\t.,;:\\\\\\'\\\\\\\"()?!\\\"\"");
                            Instances atestDataLemmaBiGramString = Utilities.applyStringToWordVectorFilter(atestDataLemmaUniGramString,
                                    "-R 19 -P lb_ -W 1000 -prune-rate -1.0 -N 0 -stemmer weka.core.stemmers.NullStemmer -stopwords-handler weka.core.stopwords.Null -M 1 -tokenizer \"weka.core.tokenizers.WordTokenizer -delimiters \\\" \\\\r\\\\n\\\\t.,;:\\\\\\'\\\\\\\"()?!\\\"\"");
                            Instances atestDataLemmaUniGramVCString = Utilities.applyStringToWordVectorFilter(atestDataLemmaBiGramString,
                                    "-R 19 -P luvc_ -W 1000 -prune-rate -1.0 -N 0 -stemmer weka.core.stemmers.NullStemmer -stopwords-handler weka.core.stopwords.Null -M 1 -tokenizer \"weka.core.tokenizers.WordTokenizer -delimiters \\\" \\\\r\\\\n\\\\t.,;:\\\\\\'\\\\\\\"()?!\\\"\"");
                            Instances atestDataLemmaBiGramVCString = Utilities.applyStringToWordVectorFilter(atestDataLemmaUniGramVCString,
                                    "-R 19 -P lbvc_ -W 1000 -prune-rate -1.0 -N 0 -stemmer weka.core.stemmers.NullStemmer -stopwords-handler weka.core.stopwords.Null -M 1 -tokenizer \"weka.core.tokenizers.WordTokenizer -delimiters \\\" \\\\r\\\\n\\\\t.,;:\\\\\\'\\\\\\\"()?!\\\"\"");
                            Instances atestDataLemmaUniGramOrderString = Utilities.applyStringToWordVectorFilter(atestDataLemmaBiGramVCString,
                                    "-R 19 -P luo_ -W 1000 -prune-rate -1.0 -N 0 -stemmer weka.core.stemmers.NullStemmer -stopwords-handler weka.core.stopwords.Null -M 1 -tokenizer \"weka.core.tokenizers.WordTokenizer -delimiters \\\" \\\\r\\\\n\\\\t.,;:\\\\\\'\\\\\\\"()?!\\\"\"");
                            Instances atestDataLemmaUniGramReverseOrderString = Utilities.applyStringToWordVectorFilter(atestDataLemmaUniGramOrderString,
                                    "-R 19 -P luro_ -W 1000 -prune-rate -1.0 -N 0 -stemmer weka.core.stemmers.NullStemmer -stopwords-handler weka.core.stopwords.Null -M 1 -tokenizer \"weka.core.tokenizers.WordTokenizer -delimiters \\\" \\\\r\\\\n\\\\t.,;:\\\\\\'\\\\\\\"()?!\\\"\"");
                            Instances atestDataFinal = Utilities.applyReorderFilter(atestDataLemmaUniGramReverseOrderString, "-R first-18,20-last,19");
                            form = Utilities.classifyInstance(atestDataFinal, 0, lemma, ainputMappedClassifier, Amap);
                        }
                    }
                    ntestData.delete();
                    vtestData.delete();
                    atestData.delete();
                } else {
                    System.out.println("The realizer only accepts tokens that contains (category, lemma and pfeatures) annotations");
                }
                //Utilities.saveResultsFile(ntestData, "nounslabeled");
                //Utilities.saveResultsFile(vtestData, "verbslabeled");
                //Utilities.saveResultsFile(atestData, "adjectiveslabeled");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("You should initialize the Realizer by initializeRealizer() before using it.");
        }
        return form;
    }


    public String getNounsTransitionsMapFileName() {
        return nounsTransitionsMapFileName;
    }

    public void setNounsTransitionsMapFileName(String nounsTransitionsMapFileName) {
        this.nounsTransitionsMapFileName = nounsTransitionsMapFileName;
    }

    public String getVerbsTransitionsMapFileName() {
        return verbsTransitionsMapFileName;
    }

    public void setVerbsTransitionsMapFileName(String verbsTransitionsMapFileName) {
        this.verbsTransitionsMapFileName = verbsTransitionsMapFileName;
    }

    public String getAdjectivesTransitionsMapFileName() {
        return adjectivesTransitionsMapFileName;
    }

    public void setAdjectivesTransitionsMapFileName(String adjectivesTransitionsMapFileName) {
        this.adjectivesTransitionsMapFileName = adjectivesTransitionsMapFileName;
    }

    public String getNounsModelFileName() {
        return nounsModelFileName;
    }

    public void setNounsModelFileName(String nounsModelFileName) {
        this.nounsModelFileName = nounsModelFileName;
    }

    public String getVerbsModelFileName() {
        return verbsModelFileName;
    }

    public void setVerbsModelFileName(String verbsModelFileName) {
        this.verbsModelFileName = verbsModelFileName;
    }

    public String getAdjectivesModelFileName() {
        return adjectivesModelFileName;
    }

    public void setAdjectivesModelFileName(String adjectivesModelFileName) {
        this.adjectivesModelFileName = adjectivesModelFileName;
    }

    public Set<Nouns.Features> getNounsFeaturesSet() {
        return nounsFeaturesSet;
    }

    public void setNounsFeaturesSet(Set<Nouns.Features> nounsFeaturesSet) {
        this.nounsFeaturesSet = nounsFeaturesSet;
    }

    public Set<Verbs.Features> getVerbsFeaturesSet() {
        return verbsFeaturesSet;
    }

    public void setVerbsFeaturesSet(Set<Verbs.Features> verbsFeaturesSet) {
        this.verbsFeaturesSet = verbsFeaturesSet;
    }

    public Set<Adjectives.Features> getAdjectivesFeaturesSet() {
        return adjectivesFeaturesSet;
    }

    public void setAdjectivesFeaturesSet(Set<Adjectives.Features> adjectivesFeaturesSet) {
        this.adjectivesFeaturesSet = adjectivesFeaturesSet;
    }
}