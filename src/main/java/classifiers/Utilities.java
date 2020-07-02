package classifiers;

import realizer.Transactions;
import weka.classifiers.Classifier;
import weka.classifiers.misc.InputMappedClassifier;
import weka.classifiers.misc.SerializedClassifier;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NominalToString;
import weka.filters.unsupervised.attribute.Reorder;
import weka.filters.unsupervised.attribute.StringToWordVector;

import java.io.*;
import java.util.*;

/**
 * Created by Ahmed on 1/23/16.
 */
public class Utilities {

    public static int computeLemmaLength(String lemma) {
        return lemma.length();
    }

    public static String computeLemmaLast(String lemma) {
        lemma = lemma.toLowerCase();
        if (lemma.matches("(?:\\p{L}\\p{M}*\\s*)+")) {
            if (lemma.length() > 0) {
                return lemma.substring(lemma.length() - 1);
            }
        }
        return null;
    }

    public static String computeLemmaLastVC(String lemma) {
        lemma = lemma.toLowerCase();
        if (lemma.matches("(?:\\p{L}\\p{M}*\\s*)+")) {
            String lemmaVC = lemma.replaceAll("[^aeiouáéíóúü\\s]", "c").replaceAll("[^c\\s]", "v");
            if (lemmaVC.length() > 0) {
                return lemmaVC.substring(lemma.length() - 1);
            }
        }
        return null;
    }

    public static String computeLemmaLast1(String lemma) {
        lemma = lemma.toLowerCase();
        if (lemma.matches("(?:\\p{L}\\p{M}*\\s*)+")) {
            if (lemma.length() > 1) {
                return lemma.substring(lemma.length() - 2, lemma.length() - 1);
            }
        }
        return null;
    }

    public static String computeLemmaLast1VC(String lemma) {
        lemma = lemma.toLowerCase();
        if (lemma.matches("(?:\\p{L}\\p{M}*\\s*)+")) {
            String lemmaVC = lemma.replaceAll("[^aeiouáéíóúü\\s]", "c").replaceAll("[^c\\s]", "v");
            if (lemmaVC.length() > 1) {
                return lemmaVC.substring(lemma.length() - 2, lemma.length() - 1);
            }
        }
        return null;
    }

    public static String computeLemmaLastBi(String lemma) {
        lemma = lemma.toLowerCase();
        if (lemma.matches("(?:\\p{L}\\p{M}*\\s*)+")) {
            if (lemma.length() > 1) {
                return lemma.substring(lemma.length() - 2);
            }
        }
        return null;
    }

    public static String computeLemmaLastBiVC(String lemma) {
        lemma = lemma.toLowerCase();
        if (lemma.matches("(?:\\p{L}\\p{M}*\\s*)+")) {
            String lemmaVC = lemma.replaceAll("[^aeiouáéíóúü\\s]", "c").replaceAll("[^c\\s]", "v");
            if (lemmaVC.length() > 1) {
                return lemmaVC.substring(lemma.length() - 2);
            }
        }
        return null;
    }

    public static String computeLemmaLast2(String lemma) {
        lemma = lemma.toLowerCase();
        if (lemma.matches("(?:\\p{L}\\p{M}*\\s*)+")) {
            if (lemma.length() > 2) {
                return lemma.substring(lemma.length() - 3, lemma.length() - 2);
            }
        }
        return null;
    }

    public static String computeLemmaLast2VC(String lemma) {
        lemma = lemma.toLowerCase();
        if (lemma.matches("(?:\\p{L}\\p{M}*\\s*)+")) {
            String lemmaVC = lemma.replaceAll("[^aeiouáéíóúü\\s]", "c").replaceAll("[^c\\s]", "v");
            if (lemmaVC.length() > 2) {
                return lemmaVC.substring(lemma.length() - 3, lemma.length() - 2);
            }
        }
        return null;
    }

    public static String computeLemmaLastTri(String lemma) {
        lemma = lemma.toLowerCase();
        if (lemma.matches("(?:\\p{L}\\p{M}*\\s*)+")) {
            if (lemma.length() > 2) {
                return lemma.substring(lemma.length() - 3);
            }
        }
        return null;
    }

    public static String computeLemmaLastTriVC(String lemma) {
        lemma = lemma.toLowerCase();
        if (lemma.matches("(?:\\p{L}\\p{M}*\\s*)+")) {
            String lemmaVC = lemma.replaceAll("[^aeiouáéíóúü\\s]", "c").replaceAll("[^c\\s]", "v");
            if (lemmaVC.length() > 2) {
                return lemmaVC.substring(lemma.length() - 3);
            }
        }
        return null;
    }

    public static String computeLemmaLastSkipBi(String lemma) {
        lemma = lemma.toLowerCase();
        if (lemma.matches("(?:\\p{L}\\p{M}*\\s*)+")) {
            if (lemma.length() > 2) {
                return lemma.substring(lemma.length() - 3, lemma.length() - 2) + "_" + lemma.substring(lemma.length() - 1);
            }
        }
        return null;
    }

    public static String computeLemmaLastSkipBiVC(String lemma) {
        lemma = lemma.toLowerCase();
        if (lemma.matches("(?:\\p{L}\\p{M}*\\s*)+")) {
            String lemmaVC = lemma.replaceAll("[^aeiouáéíóúü\\s]", "c").replaceAll("[^c\\s]", "v");
            if (lemmaVC.length() > 2) {
                return lemmaVC.substring(lemmaVC.length() - 3, lemmaVC.length() - 2) + "_" + lemmaVC.substring(lemmaVC.length() - 1);
            }
        }
        return null;
    }

    public static String computeLemmaLast1SkipBi(String lemma) {
        lemma = lemma.toLowerCase();
        if (lemma.matches("(?:\\p{L}\\p{M}*\\s*)+")) {
            if (lemma.length() > 3) {
                return lemma.substring(lemma.length() - 4, lemma.length() - 3) + "_"
                        + lemma.substring(lemma.length() - 2, lemma.length() - 1);
            }
        }
        return null;
    }

    public static String computeLemmaLast1SkipBiVC(String lemma) {
        lemma = lemma.toLowerCase();
        if (lemma.matches("(?:\\p{L}\\p{M}*\\s*)+")) {
            String lemmaVC = lemma.replaceAll("[^aeiouáéíóúü\\s]", "c").replaceAll("[^c\\s]", "v");
            if (lemmaVC.length() > 3) {
                return lemmaVC.substring(lemmaVC.length() - 4, lemmaVC.length() - 3) + "_"
                        + lemmaVC.substring(lemmaVC.length() - 2, lemmaVC.length() - 1);
            }
        }
        return null;
    }

    public static String computeLemmaUniGramString(String lemma) {
        StringBuilder LemmaUniGramStringvalue = new StringBuilder();
        for (int i = 0; i < lemma.toCharArray().length; i++) {
            LemmaUniGramStringvalue.append(lemma.charAt(i));
            LemmaUniGramStringvalue.append(" ");
        }
        return LemmaUniGramStringvalue.toString().trim();
    }

    public static String computeLemmaBiGramString(String lemma) {
        StringBuilder LemmaBiGramStringvalue = new StringBuilder();
        for (int i = 0; i < lemma.toCharArray().length - 1; i++) {
            LemmaBiGramStringvalue.append(lemma.charAt(i));
            LemmaBiGramStringvalue.append(lemma.charAt(i + 1));
            LemmaBiGramStringvalue.append(" ");
        }
        return LemmaBiGramStringvalue.toString().trim();
    }

    public static String computeLemmaUniGramVCString(String lemma) {
        StringBuilder LemmaUniGramVCStringvalue = new StringBuilder();
        if (lemma.matches("(?:\\p{L}\\p{M}*\\s*)+")) {
            String lemmaVC = lemma.replaceAll("[^aeiouáéíóúü\\s]", "c").replaceAll("[^c\\s]", "v");
            for (int i = 0; i < lemmaVC.toCharArray().length; i++) {
                LemmaUniGramVCStringvalue.append(lemmaVC.charAt(i));
                LemmaUniGramVCStringvalue.append(" ");
            }
        }
        return LemmaUniGramVCStringvalue.toString().trim();
    }

    public static String computeLemmaBiGramVCString(String lemma) {
        StringBuilder LemmaBiGramVCStringvalue = new StringBuilder();
        if (lemma.matches("(?:\\p{L}\\p{M}*\\s*)+")) {
            String lemmaVC = lemma.replaceAll("[^aeiouáéíóúü\\s]", "c").replaceAll("[^c\\s]", "v");
            for (int i = 0; i < lemmaVC.toCharArray().length - 1; i++) {
                LemmaBiGramVCStringvalue.append(lemmaVC.charAt(i));
                LemmaBiGramVCStringvalue.append(lemmaVC.charAt(i + 1));
                LemmaBiGramVCStringvalue.append(" ");
            }
        }
        return LemmaBiGramVCStringvalue.toString().trim();
    }

    public static String computeLemmaUniGramOrderString(String lemma) {
        StringBuilder LemmaUniGramOrderString = new StringBuilder();

        for (int i = 0; i < lemma.toCharArray().length; i++) {
            LemmaUniGramOrderString.append(lemma.charAt(i));
            LemmaUniGramOrderString.append(i + 1);
            LemmaUniGramOrderString.append(" ");
        }
        return LemmaUniGramOrderString.toString().trim();
    }

    public static String computeLemmaUniGramReverseOrderString(String lemma) {
        StringBuilder LemmaUniGramReverseOrderString = new StringBuilder();
        for (int i = 0; i < lemma.toCharArray().length; i++) {
            LemmaUniGramReverseOrderString.append(lemma.charAt(i));
            LemmaUniGramReverseOrderString.append(lemma.toCharArray().length - i);
            LemmaUniGramReverseOrderString.append(" ");
        }
        return LemmaUniGramReverseOrderString.toString().trim();
    }

    public static HashMap<Integer, String> loadTransitions(String mapFile) {
        HashMap<Integer, String> maps = null;
        BufferedReader mapreader;
        try {
            maps = new HashMap<Integer, String>();
            mapreader = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/" + mapFile + ".txt"), "UTF-8"));

            String Nline;

            while ((Nline = mapreader.readLine()) != null) {
                String[] tmp = Nline.split("\t");
                maps.put(Integer.parseInt(tmp[1]), tmp[0]);
            }
            mapreader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maps;
    }

    public static Instances loadTestData(String modelName) throws Exception {
        return (Instances) SerializationHelper.readAll("src/main/resources/" + modelName + ".model")[1];
    }

    public static Classifier loadClassifier(String modelName) throws Exception {
        return (Classifier) SerializationHelper.readAll("src/main/resources/" + modelName + ".model")[0];
    }

    public static InputMappedClassifier loadInputMappedClassifier(String modelName) throws Exception {
        InputMappedClassifier inputMappedClassifier = new InputMappedClassifier();
        inputMappedClassifier.setClassifier((Classifier) SerializationHelper.readAll("src/main/resources/" + modelName + ".model")[0]);
        try {
            inputMappedClassifier.setModelPath("src/main/resources/" + modelName + ".model");
        } catch (Exception e) {
            e.printStackTrace();
        }
        inputMappedClassifier.setModelHeader((Instances) SerializationHelper.readAll("src/main/resources/" + modelName + ".model")[1]);
        inputMappedClassifier.setSuppressMappingReport(true);
        inputMappedClassifier.setDebug(false);
        return inputMappedClassifier;
    }

    public static void saveResultsFile(Instances testData, String fileName) {
        Instances labeled = new Instances(testData);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("src/main/resources/" + fileName + ".arff"));

            writer.write(labeled.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String classifyInstance(Instances testData, int index, String lemma, InputMappedClassifier inputMappedClassifier, HashMap<Integer, String> maps) {
        double clsLabel = 0;
        String testform = null;

        try {
            clsLabel = inputMappedClassifier.classifyInstance(testData.instance(index));
            testData.instance(index).setClassValue(clsLabel);

            String key = testData.instance(index).stringValue(testData.instance(index).numAttributes() - 1);
            String trans = maps.get(Integer.parseInt(key));

            if (key != null && trans != null) {
                if (maps.get(Integer.parseInt(key)).equals("Do_Nothing")) {
                    testform = lemma;
                } else {
                    Transactions transactions = new Transactions();
                    testform = transactions.execute(lemma, trans);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return testform;
    }

    public static Instances applyNominalToStringVectorFilter(Instances dataSet, String options) {
        Instances instances;
        Instances filteredInstances = null;
        try {
            instances = dataSet;
            instances.setClassIndex(instances.numAttributes() - 1);

            NominalToString nominalToStringFilter = new NominalToString();
            nominalToStringFilter.setInputFormat(dataSet);
            nominalToStringFilter.setOptions(weka.core.Utils.splitOptions(options));
            filteredInstances = Filter.useFilter(instances, nominalToStringFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filteredInstances;
    }

    public static Instances applyStringToWordVectorFilter(Instances dataSet, String options) {
        Instances instances;
        Instances filteredInstances = null;
        try {
            instances = dataSet;

            StringToWordVector stringToWordVectorFilter = new StringToWordVector();
            stringToWordVectorFilter.setInputFormat(dataSet);
            stringToWordVectorFilter.setOptions(weka.core.Utils.splitOptions(options));
            filteredInstances = Filter.useFilter(instances, stringToWordVectorFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filteredInstances;
    }

    public static Instances applyReorderFilter(Instances dataSet, String options) {
        Instances instances;
        Instances filteredInstances = null;
        try {
            instances = dataSet;
            instances.setClassIndex(instances.numAttributes() - 1);

            Reorder reorderFilter = new Reorder();
            reorderFilter.setOptions(weka.core.Utils.splitOptions(options));
            reorderFilter.setInputFormat(dataSet);
            filteredInstances = Filter.useFilter(instances, reorderFilter);
            filteredInstances.setClassIndex(filteredInstances.numAttributes() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filteredInstances;
    }
}
