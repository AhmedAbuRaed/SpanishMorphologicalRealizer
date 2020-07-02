package classifiers;

import org.apache.commons.lang.StringUtils;
import weka.classifiers.misc.InputMappedClassifier;
import weka.classifiers.misc.SerializedClassifier;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import java.io.*;
import java.util.*;

/**
 * Created by Ahmed on 1/22/16.
 */
public class Verbs {
    public static enum Features {
        PER, Num, Tense, Mood, LemmaLength, LemmaLast,
        LemmaLast1, LemmaLast2, LemmaLastBi,
        LemmaLastTri, LemmaLastSkipBi, LemmaLast1SkipBi,
        LemmaLastVC, LemmaLast1VC, LemmaLast2VC,
        LemmaLastBiVC, LemmaLastTriVC, LemmaLastSkipBiVC,
        LemmaLast1SkipBiVC, LemmaUniGramString,
        LemmaBiGramString, LemmaUniGramVCString,
        LemmaBiGramVCString, LemmaUniGramOrderString, LemmaUniGramReverseOrderString
    }

    public Verbs() {

    }

    public static String computeNumber(String extraVerbFeatures) {
        String[] features = extraVerbFeatures.split("\\|");
        for (String ext : features) {
            if (ext.contains("num")) {
                int eq = ext.lastIndexOf("=");
                return ext.substring(eq + 1);
            }
        }
        return null;
    }

    public static String computePerson(String extraVerbFeatures) {
        String[] features = extraVerbFeatures.split("\\|");
        for (String ext : features) {
            if (ext.contains("person")) {
                int eq = ext.lastIndexOf("=");
                return ext.substring(eq + 1);
            }
        }
        return null;
    }

    public static String computeTense(String extraVerbFeatures) {
        String[] features = extraVerbFeatures.split("\\|");
        for (String ext : features) {
            if (ext.contains("tense")) {
                int eq = ext.lastIndexOf("=");
                return ext.substring(eq + 1);
            }
        }
        return null;
    }

    public static String computeMood(String extraVerbFeatures) {
        String[] features = extraVerbFeatures.split("\\|");
        for (String ext : features) {
            if (ext.contains("mood")) {
                int eq = ext.lastIndexOf("=");
                return ext.substring(eq + 1);
            }
        }
        return null;
    }

    public static HashMap<Features, String> computeFeatures(Set<Features> featuresSet, String lemma, String extra) {
        HashMap<Features, String> featuresMap = new HashMap<>();
        for (Features feature : featuresSet) {
            switch (feature) {
                case PER:
                    if (Verbs.computePerson(extra) != null) {
                        featuresMap.put(Features.PER, Verbs.computePerson(extra));
                    }
                    break;
                case Num:
                    if (Verbs.computeNumber(extra) != null) {
                        featuresMap.put(Features.Num, Verbs.computeNumber(extra));
                    }
                    break;
                case Tense:
                    if (Verbs.computeTense(extra) != null) {
                        featuresMap.put(Features.Tense, Verbs.computeTense(extra));
                    }
                    break;
                case Mood:
                    if (Verbs.computeMood(extra) != null) {
                        featuresMap.put(Features.Mood, Verbs.computeMood(extra));
                    }
                    break;
                case LemmaLength:
                    featuresMap.put(Features.LemmaLength, String.valueOf(Utilities.computeLemmaLength(lemma)));
                    break;
                case LemmaLast:
                    if (Utilities.computeLemmaLast(lemma) != null) {
                        featuresMap.put(Features.LemmaLast, Utilities.computeLemmaLast(lemma));
                    }
                    break;
                case LemmaLast1:
                    if (Utilities.computeLemmaLast1(lemma) != null) {
                        featuresMap.put(Features.LemmaLast1, Utilities.computeLemmaLast1(lemma));
                    }
                    break;
                case LemmaLast2:
                    if (Utilities.computeLemmaLast2(extra) != null) {
                        featuresMap.put(Features.LemmaLast2, Utilities.computeLemmaLast2(lemma));
                    }
                    break;
                case LemmaLastBi:
                    if (Utilities.computeLemmaLastBi(lemma) != null) {
                        featuresMap.put(Features.LemmaLastBi, Utilities.computeLemmaLastBi(lemma));
                    }
                    break;
                case LemmaLastTri:
                    if (Utilities.computeLemmaLastTri(lemma) != null) {
                        featuresMap.put(Features.LemmaLastTri, Utilities.computeLemmaLastTri(lemma));
                    }
                    break;
                case LemmaLastSkipBi:
                    if (Utilities.computeLemmaLastSkipBi(lemma) != null) {
                        featuresMap.put(Features.LemmaLastSkipBi, Utilities.computeLemmaLastSkipBi(lemma));
                    }
                    break;
                case LemmaLast1SkipBi:
                    if (Utilities.computeLemmaLast1SkipBi(lemma) != null) {
                        featuresMap.put(Features.LemmaLast1SkipBi, Utilities.computeLemmaLast1SkipBi(lemma));
                    }
                    break;
                case LemmaLastVC:
                    if (Utilities.computeLemmaLastVC(lemma) != null) {
                        featuresMap.put(Features.LemmaLastVC, Utilities.computeLemmaLastVC(lemma));
                    }
                    break;
                case LemmaLast1VC:
                    if (Utilities.computeLemmaLast1VC(lemma) != null) {
                        featuresMap.put(Features.LemmaLast1VC, Utilities.computeLemmaLast1VC(lemma));
                    }
                    break;
                case LemmaLast2VC:
                    if (Utilities.computeLemmaLast2VC(lemma) != null) {
                        featuresMap.put(Features.LemmaLast2VC, Utilities.computeLemmaLast2VC(lemma));
                    }
                    break;
                case LemmaLastBiVC:
                    if (Utilities.computeLemmaLastBiVC(lemma) != null) {
                        featuresMap.put(Features.LemmaLastBiVC, Utilities.computeLemmaLastBiVC(lemma));
                    }
                    break;
                case LemmaLastTriVC:
                    if (Utilities.computeLemmaLastTriVC(lemma) != null) {
                        featuresMap.put(Features.LemmaLastTriVC, Utilities.computeLemmaLastTriVC(lemma));
                    }
                    break;
                case LemmaLastSkipBiVC:
                    if (Utilities.computeLemmaLastSkipBiVC(lemma) != null) {
                        featuresMap.put(Features.LemmaLastSkipBiVC, Utilities.computeLemmaLastSkipBiVC(lemma));
                    }
                    break;
                case LemmaLast1SkipBiVC:
                    if (Utilities.computeLemmaLast1SkipBiVC(lemma) != null) {
                        featuresMap.put(Features.LemmaLast1SkipBiVC, Utilities.computeLemmaLast1SkipBiVC(lemma));
                    }
                    break;
                case LemmaUniGramString:
                    if (Utilities.computeLemmaUniGramString(lemma) != null) {
                        featuresMap.put(Features.LemmaUniGramString, Utilities.computeLemmaUniGramString(lemma));
                    }
                    break;
                case LemmaBiGramString:
                    if (Utilities.computeLemmaBiGramString(lemma) != null) {
                        featuresMap.put(Features.LemmaBiGramString, Utilities.computeLemmaBiGramString(lemma));
                    }
                    break;
                case LemmaUniGramVCString:
                    if (Utilities.computeLemmaUniGramVCString(lemma) != null) {
                        featuresMap.put(Features.LemmaUniGramVCString, Utilities.computeLemmaUniGramVCString(lemma));
                    }
                    break;
                case LemmaBiGramVCString:
                    if (Utilities.computeLemmaBiGramVCString(lemma) != null) {
                        featuresMap.put(Features.LemmaBiGramVCString, Utilities.computeLemmaBiGramVCString(lemma));
                    }
                    break;
                case LemmaUniGramOrderString:
                    if(Utilities.computeLemmaUniGramOrderString(lemma) != null)
                    {
                        featuresMap.put(Features.LemmaUniGramOrderString, Utilities.computeLemmaUniGramOrderString(lemma));
                    }
                case LemmaUniGramReverseOrderString:
                    if(Utilities.computeLemmaUniGramReverseOrderString(lemma) != null)
                    {
                        featuresMap.put(Features.LemmaUniGramReverseOrderString, Utilities.computeLemmaUniGramReverseOrderString(lemma));
                    }
            }
        }
        return featuresMap;
    }

    public Instances loadTestInstance(int index, HashMap<Features, String> featuresMap, ArrayList<Attribute> features) {
        // Create the instance
        Instances verbsDataset = new Instances("Verbs", features, 0);
        verbsDataset.setClassIndex(verbsDataset.numAttributes() - 1);

        Instance instance = new DenseInstance(27);
        verbsDataset.add(instance);

        verbsDataset.instance(index).setValue(0, "v");
        for (Features feature : featuresMap.keySet()) {
            if (!(feature.toString().equals("LemmaUniGramString"))  &&
                    !(feature.toString().equals("LemmaBiGramString")) &&
                    !(feature.toString().equals("LemmaUniGramVCString")) &&
                    !(feature.toString().equals("LemmaBiGramVCString")) &&
                    !(feature.toString().equals("LemmaUniGramOrderString")) &&
                    !(feature.toString().equals("LemmaUniGramReverseOrderString"))) {
                Attribute attribute = verbsDataset.attribute(feature.toString());
                if (attribute.isNumeric()) {
                    verbsDataset.instance(index).setValue(attribute, Integer.parseInt(featuresMap.get(feature)));
                } else if (attribute.isNominal()) {
                    boolean exists = false;
                    Enumeration enumeration = attribute.enumerateValues();

                    while (enumeration.hasMoreElements()) {
                        Object element = enumeration.nextElement();
                        if (element.toString().equals(featuresMap.get(feature))) {
                            exists = true;
                            break;
                        }
                    }

                    if (exists) {
                        verbsDataset.instance(index).setValue(attribute, featuresMap.get(feature));
                    } else {
                        verbsDataset.instance(index).setMissing(attribute);
                    }
                }
            }
        }

        verbsDataset.instance(index).setValue(20, featuresMap.get(Features.LemmaUniGramString));
        verbsDataset.instance(index).setValue(21, featuresMap.get(Features.LemmaBiGramString));
        verbsDataset.instance(index).setValue(22, featuresMap.get(Features.LemmaUniGramVCString));
        verbsDataset.instance(index).setValue(23, featuresMap.get(Features.LemmaBiGramVCString));
        verbsDataset.instance(index).setValue(24, featuresMap.get(Features.LemmaUniGramOrderString));
        verbsDataset.instance(index).setValue(25, featuresMap.get(Features.LemmaUniGramReverseOrderString));

        //add the instance to instances
        verbsDataset.instance(index).setClassMissing();

        return verbsDataset;
    }

    public static HashMap<String, HashMap<String, List<List<String>>>> loadIrrVerbs() {
        HashMap<String, HashMap<String, List<List<String>>>> irrVerb = new HashMap<String, HashMap<String, List<List<String>>>>();

        File inDir = new File("src/main/resources/verbos_irregulares/");

        File[] flist = inDir.listFiles();

        for (File file : flist) {
            //INDICATIVO
            List<String> listINDICATIVO = new ArrayList<String>();
            List<List<String>> matrixINDICATIVO = new ArrayList<List<String>>();
            HashMap<String, List<List<String>>> categoriesHash = new HashMap<String, List<List<String>>>();
            BufferedReader readerINDICATIVO;
            try {
                readerINDICATIVO = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(file), "UTF-8"));

                boolean flagINDICATIVO = false;
                String str;
                while ((str = readerINDICATIVO.readLine()) != null) {
                    if (!str.contains("/") && !StringUtils.isAllUpperCase(str) && !str.equals("presente") &&
                            !str.equals("infinitivo") && !str.equals("participio") && !str.equals("gerundio") && !flagINDICATIVO) {
                        listINDICATIVO.add(str);
                    } else if (str.equals("SUBJUNTIVO")) {
                        flagINDICATIVO = true;
                        break;
                    }
                }
                if (flagINDICATIVO) {
                    for (int i = 0; i < listINDICATIVO.size(); i += 6) {
                        List<String> group = listINDICATIVO.subList(i, i + 6);
                        matrixINDICATIVO.add(group);
                    }

                    categoriesHash.put("INDICATIVO", matrixINDICATIVO);

                    //System.out.println(categoriesHash);
                }
                readerINDICATIVO.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            //SUBJUNTIVO
            List<String> listSUBJUNTIVO = new ArrayList<String>();
            List<List<String>> matrixSUBJUNTIVO = new ArrayList<List<String>>();
            BufferedReader readerSUBJUNTIVO;
            try {
                readerSUBJUNTIVO = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(file), "UTF-8"));
                boolean flagT = false;
                boolean flagSUBJUNTIVO = false;
                String str;
                while ((str = readerSUBJUNTIVO.readLine()) != null) {
                    if (!str.equals("SUBJUNTIVO") && !flagT) {
                        continue;
                    } else if (str.equals("SUBJUNTIVO")) {
                        flagT = true;
                    }

                    if (!str.contains("/") && !StringUtils.isAllUpperCase(str) && !str.equals("presente") &&
                            !str.equals("infinitivo") && !str.equals("participio") && !str.equals("gerundio") && !flagSUBJUNTIVO && flagT) {
                        listSUBJUNTIVO.add(str);
                    } else if (str.equals("IMPERATIVO")) {
                        flagSUBJUNTIVO = true;
                        break;
                    }
                }
                if (flagSUBJUNTIVO) {
                    for (int i = 0; i < listSUBJUNTIVO.size(); i += 6) {
                        List<String> group = listSUBJUNTIVO.subList(i, i + 6);
                        matrixSUBJUNTIVO.add(group);
                    }

                    categoriesHash.put("SUBJUNTIVO", matrixSUBJUNTIVO);

                    //System.out.println(categoriesHash);
                }
                readerSUBJUNTIVO.close();

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            //IMPERATIVO
            List<String> listIMPERATIVO = new ArrayList<String>();
            List<List<String>> matrixIMPERATIVO = new ArrayList<List<String>>();
            BufferedReader readerIMPERATIVO;
            try {
                readerIMPERATIVO = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(file), "UTF-8"));

                boolean flagIMPERATIVO = false;
                String str;
                boolean flagT = false;
                while ((str = readerIMPERATIVO.readLine()) != null) {
                    if (!str.equals("IMPERATIVO") && !flagT) {
                        continue;
                    } else if (str.equals("IMPERATIVO")) {
                        flagT = true;
                    }

                    if (!str.contains("/") && !StringUtils.isAllUpperCase(StringUtils.deleteWhitespace(str)) && !str.equals("presente") &&
                            !str.equals("infinitivo") && !str.equals("participio") && !str.equals("gerundio") && !flagIMPERATIVO) {
                        listIMPERATIVO.add(str);
                    } else if (str.equals("FORMAS NO PERSONALES")) {
                        flagIMPERATIVO = true;
                        break;
                    }
                }
                if (flagIMPERATIVO) {
                    for (int i = 0; i < listIMPERATIVO.size(); i += 2) {
                        List<String> group = listIMPERATIVO.subList(i, i + 2);
                        matrixIMPERATIVO.add(group);
                    }

                    categoriesHash.put("IMPERATIVO", matrixIMPERATIVO);

                    //System.out.println(categoriesHash);
                }
                readerIMPERATIVO.close();

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            //FORMAS NO PERSONALES
            List<String> listFORMAS = new ArrayList<String>();
            List<List<String>> matrixFORMAS = new ArrayList<List<String>>();
            BufferedReader readerFORMAS;
            try {
                readerFORMAS = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(file), "UTF-8"));

                String str;
                boolean flagT = false;
                while ((str = readerFORMAS.readLine()) != null) {
                    if (!str.equals("FORMAS NO PERSONALES") && !flagT) {
                        continue;
                    } else if (str.equals("FORMAS NO PERSONALES")) {
                        flagT = true;
                    }

                    if (!str.contains("/") && !StringUtils.isAllUpperCase(StringUtils.deleteWhitespace(str)) && !str.equals("presente") &&
                            !str.equals("infinitivo") && !str.equals("participio") && !str.equals("gerundio")) {
                        listFORMAS.add(str);
                    }
                }

                for (int i = 0; i < listFORMAS.size(); i += 3) {
                    List<String> group = listFORMAS.subList(i, i + 3);
                    matrixFORMAS.add(group);
                }

                categoriesHash.put("FORMAS NO PERSONALES", matrixFORMAS);

                //System.out.println(categoriesHash);

                readerFORMAS.close();


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //set Hashmap
            irrVerb.put(file.getName().substring(0, file.getName().length() - 4), categoriesHash);
        }

        return irrVerb;
    }

    public String getIrrVerbForm(HashMap<String, HashMap<String, List<List<String>>>> irrVerb, String lemma, String mood, String tense, String number, String person) {
        String testform = lemma;
        switch (mood) {
            case "indicative":
                if (tense.equals("present")) {
                    if (number.equals("s")) {
                        if (person.equals("1")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("INDICATIVO").get(0).get(0);
                        } else if (person.equals("2")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("INDICATIVO").get(0).get(1);
                        } else if (person.equals("3")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("INDICATIVO").get(0).get(2);
                        }
                    } else if (number.equals("p")) {
                        if (person.equals("1")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("INDICATIVO").get(0).get(3);
                        } else if (person.equals("2")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("INDICATIVO").get(0).get(4);
                        } else if (person.equals("3")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("INDICATIVO").get(0).get(5);
                        }
                    }
                } else if (tense.equals("imperfect")) {
                    if (number.equals("s")) {
                        if (person.equals("1")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("INDICATIVO").get(1).get(0);
                        } else if (person.equals("2")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("INDICATIVO").get(1).get(1);
                        } else if (person.equals("3")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("INDICATIVO").get(1).get(2);
                        }
                    } else if (number.equals("p")) {
                        if (person.equals("1")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("INDICATIVO").get(1).get(3);
                        } else if (person.equals("2")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("INDICATIVO").get(1).get(4);
                        } else if (person.equals("3")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("INDICATIVO").get(1).get(5);
                        }
                    }
                } else if (tense.equals("past")) {
                    if (number.equals("s")) {
                        if (person.equals("1")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("INDICATIVO").get(2).get(0);
                        } else if (person.equals("2")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("INDICATIVO").get(2).get(1);
                        } else if (person.equals("3")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("INDICATIVO").get(2).get(2);
                        }
                    } else if (number.equals("p")) {
                        if (person.equals("1")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("INDICATIVO").get(2).get(3);
                        } else if (person.equals("2")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("INDICATIVO").get(2).get(4);
                        } else if (person.equals("3")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("INDICATIVO").get(2).get(5);
                        }
                    }
                } else if (tense.equals("future")) {
                    if (number.equals("s")) {
                        if (person.equals("1")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("INDICATIVO").get(3).get(0);
                        } else if (person.equals("2")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("INDICATIVO").get(3).get(1);
                        } else if (person.equals("3")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("INDICATIVO").get(3).get(2);
                        }
                    } else if (number.equals("p")) {
                        if (person.equals("1")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("INDICATIVO").get(3).get(3);
                        } else if (person.equals("2")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("INDICATIVO").get(3).get(4);
                        } else if (person.equals("3")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("INDICATIVO").get(3).get(5);
                        }
                    }
                } else if (tense.equals("conditional")) {
                    if (number.equals("s")) {
                        if (person.equals("1")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("INDICATIVO").get(4).get(0);
                        } else if (person.equals("2")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("INDICATIVO").get(4).get(1);
                        } else if (person.equals("3")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("INDICATIVO").get(4).get(2);
                        }
                    } else if (number.equals("p")) {
                        if (person.equals("1")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("INDICATIVO").get(4).get(3);
                        } else if (person.equals("2")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("INDICATIVO").get(4).get(4);
                        } else if (person.equals("3")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("INDICATIVO").get(4).get(5);
                        }
                    }
                }
                break;
            case "infinitive":
                testform = irrVerb.get(lemma.toUpperCase()).get("FORMAS NO PERSONALES").get(0).get(0);

                break;
            case "gerund":
                testform = irrVerb.get(lemma.toUpperCase()).get("FORMAS NO PERSONALES").get(0).get(2);

                break;
            case "participle":
                testform = irrVerb.get(lemma.toUpperCase()).get("FORMAS NO PERSONALES").get(0).get(1);

                break;
            case "subjunctive":
                if (tense.equals("present")) {
                    if (number.equals("s")) {
                        if (person.equals("1")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("SUBJUNTIVO").get(0).get(0);
                        } else if (person.equals("2")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("SUBJUNTIVO").get(0).get(1);
                        } else if (person.equals("3")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("SUBJUNTIVO").get(0).get(2);
                        }
                    } else if (number.equals("p")) {
                        if (person.equals("1")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("SUBJUNTIVO").get(0).get(3);
                        } else if (person.equals("2")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("SUBJUNTIVO").get(0).get(4);
                        } else if (person.equals("3")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("SUBJUNTIVO").get(0).get(5);
                        }
                    }
                } else if (tense.equals("past")) {
                    if (number.equals("s")) {
                        if (person.equals("1")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("SUBJUNTIVO").get(1).get(0);
                        } else if (person.equals("2")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("SUBJUNTIVO").get(1).get(1);
                        } else if (person.equals("3")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("SUBJUNTIVO").get(1).get(2);
                        }
                    } else if (number.equals("p")) {
                        if (person.equals("1")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("SUBJUNTIVO").get(1).get(3);
                        } else if (person.equals("2")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("SUBJUNTIVO").get(1).get(4);
                        } else if (person.equals("3")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("SUBJUNTIVO").get(1).get(5);
                        }
                    }
                } else if (tense.equals("future")) {
                    if (number.equals("s")) {
                        if (person.equals("1")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("SUBJUNTIVO").get(2).get(0);
                        } else if (person.equals("2")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("SUBJUNTIVO").get(2).get(1);
                        } else if (person.equals("3")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("SUBJUNTIVO").get(2).get(2);
                        }
                    } else if (number.equals("p")) {
                        if (person.equals("1")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("SUBJUNTIVO").get(2).get(3);
                        } else if (person.equals("2")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("SUBJUNTIVO").get(2).get(4);
                        } else if (person.equals("3")) {
                            testform = irrVerb.get(lemma.toUpperCase()).get("SUBJUNTIVO").get(2).get(5);
                        }
                    }
                }
                break;
            case "imperative":
                if (number.equals("s")) {
                    testform = irrVerb.get(lemma.toUpperCase()).get("IMPERATIVO").get(0).get(0);
                } else if (number.equals("p")) {
                    testform = irrVerb.get(lemma.toUpperCase()).get("IMPERATIVO").get(0).get(1);
                }
                break;
        }
        return testform;
    }

    public static ArrayList<Attribute> generateVerbsAttributes() {
        ArrayList<Attribute> verbsFeatures = new ArrayList<Attribute>();

        verbsFeatures.add(new Attribute("Category", Arrays.asList("v")));
        verbsFeatures.add(new Attribute("PER", Arrays.asList("3","null","1","2")));
        verbsFeatures.add(new Attribute("Num", Arrays.asList("p","s","null","c")));
        verbsFeatures.add(new Attribute("Tense", Arrays.asList("past","imperfect","null","present","future","conditional")));
        verbsFeatures.add(new Attribute("Mood", Arrays.asList("indicative","infinitive","gerund","participle","subjunctive","imperative")));

        verbsFeatures.add(new Attribute("LemmaLength"));

        verbsFeatures.add(new Attribute("LemmaLast", Arrays.asList("r","e","l","n")));
        verbsFeatures.add(new Attribute("LemmaLast1", Arrays.asList("i","e","a","s","í","b","u","d")));
        verbsFeatures.add(new Attribute("LemmaLast2", Arrays.asList("b","l","m","t","r","v","c","g","u","d","e","z","y","i","s","n","j","h","ñ","a","p","f","ü","q","o","x","null")));

        verbsFeatures.add(new Attribute("LemmaLastBi", Arrays.asList("ir","er","ar","se","ír","be","el","ue","de","an")));
        verbsFeatures.add(new Attribute("LemmaLastTri", Arrays.asList("bir","ler","mar","tar","lir","tir","rar","var","car","gar","lar","uir","der","cer","eer","zar","dir","rir","gir","dar","uar","yar","iar","sar","ner","cir","nar","ter","jar","ber","ver","ger","ear","vir","bar","har","ñar","aer","nir","par","mer","rse","mir","per","rer","jer","eír","ebe","far","ñir","del","ser","pir","jir","ñer","üir","que","oar","oír","xar","null","han")));

        verbsFeatures.add(new Attribute("LemmaLastSkipBi", Arrays.asList("b_r","l_r","m_r","t_r","r_r","v_r","c_r","g_r","u_r","d_r","e_r","z_r","y_r","i_r","s_r","n_r","j_r","h_r","ñ_r","a_r","p_r","r_e","e_e","f_r","d_l","ü_r","q_e","o_r","x_r","null","h_n")));
        verbsFeatures.add(new Attribute("LemmaLast1SkipBi", Arrays.asList("u_i","a_e","r_a","s_a","p_i","c_a","i_a","r_i","t_a","o_a","n_a","e_a","l_a","g_i","u_a","n_e","e_e","r_e","e_i","b_i","a_a","i_i","s_i","o_e","l_i","n_i","g_a","p_a","a_i","l_e","b_a","t_i","d_a","f_i","i_s","v_a","m_e","m_a","z_a","o_i","e_s","s_e","f_a","h_i","a_s","x_a","ñ_a","m_i","j_a","r_í","d_b","c_e","null","s_í","v_e","h_a")));

        verbsFeatures.add(new Attribute("LemmaLastVC", Arrays.asList("c","v")));
        verbsFeatures.add(new Attribute("LemmaLast1VC", Arrays.asList("v","c")));
        verbsFeatures.add(new Attribute("LemmaLast2VC", Arrays.asList("c","v","null")));

        verbsFeatures.add(new Attribute("LemmaLastBiVC", Arrays.asList("vc","cv","vv")));
        verbsFeatures.add(new Attribute("LemmaLastTriVC", Arrays.asList("cvc","vvc","ccv","vcv","cvv","null")));

        verbsFeatures.add(new Attribute("LemmaLastSkipBiVC", Arrays.asList("c_c","v_c","c_v","v_v","null")));
        verbsFeatures.add(new Attribute("LemmaLast1SkipBiVC", Arrays.asList("v_v","c_v","v_c","c_c","null")));

        verbsFeatures.add(new Attribute("LemmaUniGramString", (ArrayList) null));
        verbsFeatures.add(new Attribute("LemmaBiGramString", (ArrayList) null));
        verbsFeatures.add(new Attribute("LemmaUniGramVCString", (ArrayList) null));
        verbsFeatures.add(new Attribute("LemmaBiGramVCString", (ArrayList) null));
        verbsFeatures.add(new Attribute("LemmaUniGramOrderString", (ArrayList) null));
        verbsFeatures.add(new Attribute("LemmaUniGramReverseOrderString", (ArrayList) null));

        verbsFeatures.add(new Attribute("Transition", Arrays.asList("1","2","3","5","7","8","9","10","11","12","13","15","16","17","18","19","20","21","22","24","25","26","27","28","29","30","31","33","34","35","36","37","38","39","40","41","44","45","46","48","50","51","53","54","55","56","57","58","59","60","61","62","63","64","66","67","68","69","70","72","74","75","76","77","78","79","80","81","82","84","86","87","88","89","90","91","92","93","94","96","98","99","100","101","103","104","105","106","108","109","111","112","113","115","116","117","118","122","123","124","125","126","127","128","129","130","131","132","134","136","137","138","139","140","142","145","146","147","148","149","150","151","152","153","155","157","158","160","161","163","164","165","166","167","168","169","172","173","174","175","177","178","180","181","182","183","184","185","186","187","188","190","191","192","193","194","196","200","201","203","204","205","207","209","210","211","218","224","226","228","229","230","231","232","235","236","237","238","239","241","242","243","246","247","250","252","253","254","256","257","258","259","260","262","263","264","265","266","268","269","272","274","275","276","278","279","280","286","288","289","291","296","297","298","299","302","304","305","306","310","311","313","314","315","317","318","319","320","321","324","325","326","327","328","329","330","332","334","336","340","341","344","346","347","348","349","352","355","357","362","364","366","367","368","371","373","374","376","377","378","380","382","383","385","389","391","398","399","400","401","402","403","405","406","409","410","411","413","416","417","418","422","424","425","426","427","428","429","430","431","432","436","437","438","441","445","446","447","448","449","450","451","452","456","458","459","461","464","468","469","472","474","475","476","477","478","480","482","483","484","485","486","488","490","491","493","494","495","496","497","498","499","500","501","502","503","504","506","508","511","513","514","517","519","521","522","523","526","529","531","532","533","535","539","540","541","542","543","544","545","547","548","549","550","551","552","553","554","556","557","558","559","560","561","562","563","564","565","566","567","568","569","570","571","572","573","574","575","576","577","578","579","580","581","582","583","584","585","586","587","588","589","590","591","592","593","594","595","596","597","598","599","600","601","602","603","604","605","606","607","608","609","610","611","612","613","614","615","616")));

        return verbsFeatures;
    }
}
