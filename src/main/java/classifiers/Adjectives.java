package classifiers;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import java.util.*;

/**
 * Created by Ahmed on 3/24/16.
 */
public class Adjectives {
    public static enum Features {
        GEN, Num, LemmaLength, LemmaLast,
        LemmaLast1, LemmaLast2, LemmaLastBi,
        LemmaLastTri, LemmaLastSkipBi, LemmaLast1SkipBi,
        LemmaLastVC, LemmaLast1VC, LemmaLast2VC,
        LemmaLastBiVC, LemmaLastTriVC, LemmaLastSkipBiVC,
        LemmaLast1SkipBiVC, LemmaUniGramString,
        LemmaBiGramString, LemmaUniGramVCString,
        LemmaBiGramVCString, LemmaUniGramOrderString, LemmaUniGramReverseOrderString
    }

    public static String computeGender(String extraAdjectiveFeatures) {
        String[] features = extraAdjectiveFeatures.split("\\|");
        for (String ext : features) {
            if (ext.contains("gen")) {
                int eq = ext.lastIndexOf("=");
                return ext.substring(eq + 1);
            }
        }
        return null;
    }

    public static String computeNumber(String extraAdjectiveFeatures) {
        String[] features = extraAdjectiveFeatures.split("\\|");
        for (String ext : features) {
            if (ext.contains("num")) {
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
                case GEN:
                    if (Adjectives.computeGender(extra) != null) {
                        featuresMap.put(Features.GEN, Adjectives.computeGender(extra));
                    }
                    break;
                case Num:
                    if (Adjectives.computeNumber(extra) != null) {
                        featuresMap.put(Features.Num, Adjectives.computeNumber(extra));
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
                    if (Utilities.computeLemmaLast2(lemma) != null) {
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
        Instances adjectivesDataset = new Instances("Adjectives", features, 0);
        adjectivesDataset.setClassIndex(adjectivesDataset.numAttributes() - 1);

        Instance instance = new DenseInstance(25);
        adjectivesDataset.add(instance);

        adjectivesDataset.instance(index).setValue(0, "a");
        for (Features feature : featuresMap.keySet()) {
            if (!(feature.toString().equals("LemmaUniGramString"))  &&
                    !(feature.toString().equals("LemmaBiGramString")) &&
                    !(feature.toString().equals("LemmaUniGramVCString")) &&
                    !(feature.toString().equals("LemmaBiGramVCString")) &&
                    !(feature.toString().equals("LemmaUniGramOrderString")) &&
                    !(feature.toString().equals("LemmaUniGramReverseOrderString"))) {
                Attribute attribute = adjectivesDataset.attribute(feature.toString());
                if (attribute.isNumeric()) {
                    adjectivesDataset.instance(index).setValue(attribute, Integer.parseInt(featuresMap.get(feature)));
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
                        adjectivesDataset.instance(index).setValue(attribute, featuresMap.get(feature));
                    } else {
                        adjectivesDataset.instance(index).setMissing(attribute);
                    }
                }
            }
        }

        adjectivesDataset.instance(index).setValue(18, featuresMap.get(Features.LemmaUniGramString));
        adjectivesDataset.instance(index).setValue(19, featuresMap.get(Features.LemmaBiGramString));
        adjectivesDataset.instance(index).setValue(20, featuresMap.get(Features.LemmaUniGramVCString));
        adjectivesDataset.instance(index).setValue(21, featuresMap.get(Features.LemmaBiGramVCString));
        adjectivesDataset.instance(index).setValue(22, featuresMap.get(Features.LemmaUniGramOrderString));
        adjectivesDataset.instance(index).setValue(23, featuresMap.get(Features.LemmaUniGramReverseOrderString));

        //add the instance to instances
        adjectivesDataset.instance(index).setClassMissing();

        return adjectivesDataset;
    }

    public static ArrayList<Attribute> generateAdjectivesAttributes() {
        ArrayList<Attribute> adjectivesFeatures = new ArrayList<Attribute>();

        adjectivesFeatures.add(new Attribute("Category", Arrays.asList("a")));
        adjectivesFeatures.add(new Attribute("GEN", Arrays.asList("f","m","c","null")));
        adjectivesFeatures.add(new Attribute("Num", Arrays.asList("s","p","null","c")));

        adjectivesFeatures.add(new Attribute("LemmaLength"));

        adjectivesFeatures.add(new Attribute("LemmaLast", Arrays.asList("o","n","l","e","r","s","a","í","x","z","y","i","é","d","ú","k","t","g","b","q","h","p","f")));
        adjectivesFeatures.add(new Attribute("LemmaLast1", Arrays.asList("d","á","a","t","ñ","n","v","c","s","i","m","r","l","o","e","é","j","g","h","z","u","x","ú","ó","í","b","p","y","f","ü")));
        adjectivesFeatures.add(new Attribute("LemmaLast2", Arrays.asList("i","l","r","n","e","a","u","g","p","b","ñ","y","c","v","t","o","d","j","s","m","null","f","q","í","z","x","ó","w","h","k")));

        adjectivesFeatures.add(new Attribute("LemmaLastBi", Arrays.asList("do","án","al","te","ño","no","vo","co","se","io","il","mo","de","ro","le","ol","eo","ve","so","ar","or","és","jo","go","to","an","en","ho","ga","zo","me","uo","ta","lí","xo","ra","re","ex","lo","la","ún","ne","az","ón","er","uí","dí","zí","ío","el","uz","iz","bo","je","ay","ca","ao","is","na","za","in","pi","sa","he","yo","po","ul","dé","oz","ue","pe","ie","rd","ox","bi","fo","lú","zi","ua","py","xy","dú","fe","ez","da","ce","ur","ín","ck","ia","be","ma","ní","es","ás","él","ad","ea","va","ir","mi","ut","ng","eb","os","as","ií","oq","ti","ch","op","ob","tt","üe","ha","íf","pa","tí","ya","ge")));
        adjectivesFeatures.add(new Attribute("LemmaLastTri", Arrays.asList("ido","lán","ral","nte","eño","ano","evo","nal","ual","ado","ico","nse","ial","rio","gal","aco","gil","emo","nde","pal","ero","ble","ñol","peo","imo","ave","iso","lar","ivo","uro","yor","cés","cal","nor","vil","tal","ijo","oco","ple","dor","rgo","rto","ran","uen","cho","dio","cio","eco","lga","izo","ato","bal","jor","rte","oso","lio","rme","tuo","lso","eno","rno","ior","tar","sta","elí","sto","ndo","reo","exo","ogo","ino","neo","ejo","mal","eso","eve","uto","nto","nuo","ora","tor","aro","bre","cil","null","sco","ulo","eta","pio","mán","odo","fal","til","ola","mún","lde","nar","ine","lto","omo","eto","bil","sil","caz","ajo","ito","eor","nés","gno","pón","nco","nso","ste","mer","cto","uso","guo","quí","dés","rdí","azí","eal","ojo","rde","año","mne","río","llo","gar","oto","paz","uel","elo","gro","cer","ído","udo","aso","rso","luz","sal","rdo","tón","liz","iar","tro","alo","ven","mbo","aje","gay","uno","jón","lés","ica","gre","olo","ués","bio","lón","nio","zón","iao","tis","ete","vío","ana","rro","cío","ime","iza","lin","tio","api","usa","che","ayo","apo","feo","tre","ego","xto","zul","pto","oro","cuo","val","rco","ata","adí","cre","odé","avo","roz","que","edo","ita","igo","rpe","ono","vio","rer","ril","ago","ear","cie","coz","bra","ord","iel","duo","uin","ame","riz","dar","sor","dox","rfo","dío","cro","nne","ite","ulú","nil","azi","rón","ilo","cua","pen","ppy","exy","lor","íno","rra","raz","ofo","era","ris","ndú","uaz","afe","ólo","teo","laz","oez","ida","umo","ceo","une","gaz","rvo","nue","ena","smo","obo","lce","sur","mil","uín","bro","ock","seo","per","dia","abe","sma","aní","llí","des","loz","sio","ove","más","les","zad","fín","sca","jar","pea","ala","eva","rta","uir","ude","gen","oca","oxo","emi","ión","uco","ive","out","ale","ing","nsa","ada","gra","udí","ení","daz","gir","ñés","ona","web","pdo","los","oga","ter","eas","osa","tés","rmo","ane","par","hií","eón","vón","doq","nti","rar","tra","lia","vel","tas","mir","rás","sch","ice","uil","hón","hno","die","mio","pop","lta","ngo","cón","ara","nob","utt","güe","naz","cha","ria","azo","bir","aíf","vés","upa","ití","ema","ios","ves","aya","luo","var","auí","gás","ota","eca","ueo","utí","lvo","kin","ead","nge","lfo","lzo","ofe")));

        adjectivesFeatures.add(new Attribute("LemmaLastSkipBi", Arrays.asList("i_o","l_n","r_l","n_e","e_o","a_o","n_l","u_l","i_l","r_o","g_l","p_l","b_e","ñ_l","p_o","a_e","l_r","u_o","y_r","c_s","c_l","n_r","v_l","t_l","o_o","p_e","d_r","r_n","u_n","c_o","d_o","l_a","b_l","j_r","r_e","l_o","t_o","i_r","t_r","s_a","e_í","s_o","n_o","m_l","e_e","o_a","null","e_a","m_n","f_l","l_e","i_e","s_l","c_z","e_r","n_s","g_o","p_n","s_e","m_r","q_í","d_s","r_í","a_í","e_l","m_e","g_r","p_z","c_r","í_o","l_z","t_n","v_n","m_o","g_y","j_n","l_s","i_a","g_e","u_s","b_o","z_n","t_s","v_o","a_a","a_i","u_a","c_e","f_o","t_e","x_o","z_l","o_é","r_z","q_e","r_r","b_a","o_d","s_r","d_x","u_ú","c_a","p_y","e_y","r_a","r_s","n_ú","u_z","ó_o","o_z","u_e","g_z","o_k","p_r","d_a","l_í","o_e","m_s","z_d","f_n","p_a","u_r","g_n","e_i","i_n","o_t","i_g","n_a","g_a","u_í","d_z","ñ_s","w_b","e_s","h_í","e_n","d_q","n_i","t_a","s_h","h_n","h_o","d_e","p_p","c_n","n_b","u_t","n_z","b_r","a_f","v_s","i_í","i_s","v_r","g_s","k_n","e_d")));
        adjectivesFeatures.add(new Attribute("LemmaLast1SkipBi", Arrays.asList("r_d","a_á","o_a","e_t","u_ñ","i_n","u_v","n_a","c_d","t_c","e_s","c_a","a_i","e_a","z_d","l_c","m_c","á_i","p_d","r_m","a_d","i_a","m_r","l_d","d_d","a_l","n_c","a_o","o_e","g_c","ü_ñ","t_m","r_v","c_s","e_i","s_d","t_d","t_v","e_d","u_a","g_d","v_r","x_m","d_r","n_é","o_i","e_o","ó_i","d_c","a_t","f_j","s_v","r_c","m_l","s_a","m_d","s_c","c_n","t_r","v_d","a_g","b_d","g_a","r_a","b_e","i_h","n_d","t_a","i_l","e_h","f_c","i_i","l_ñ","j_d","h_c","e_g","u_z","i_t","a_a","g_r","d_s","i_c","p_i","n_m","i_m","m_n","u_u","a_s","l_n","e_n","r_o","u_d","é_e","n_x","l_g","u_n","d_n","ó_e","l_j","r_s","l_t","u_t","l_r","i_u","l_o","c_r","i_r","j_r","í_i","h_n","null","í_c","a_c","n_l","o_t","t_n","d_a","e_á","n_i","c_l","r_r","o_ú","v_c","v_a","g_n","n_n","t_ñ","é_i","l_v","l_s","n_s","b_j","i_d","n_t","z_s","r_t","c_o","p_o","g_i","o_é","í_t","v_n","i_ó","i_o","ñ_d","b_a","r_z","i_e","m_i","s_m","a_u","m_a","h_d","i_s","n_z","r_j","r_ñ","p_r","f_í","e_l","l_a","e_c","o_l","o_r","m_s","r_e","l_l","r_n","d_t","e_r","j_n","e_u","a_e","v_v","o_d","e_ó","t_s","f_n","b_n","i_j","s_r","u_i","a_b","t_z","m_ñ","í_l","o_c","v_j","ú_i","x_a","a_ó","g_é","j_c","p_a","n_r","u_s","o_ó","s_l","l_ó","j_z","h_t","á_e","s_i","c_ñ","s_t","h_r","a_í","s_n","u_ó","j_p","n_h","u_y","í_a","j_t","u_p","r_g","ñ_s","h_s","u_c","ñ_t","u_h","t_o","n_u","u_r","g_s","m_g","r_i","o_p","í_e","t_t","g_l","d_z","p_c","c_t","t_e","ñ_n","i_g","o_m","m_j","p_s","é_c","c_i","a_h","f_e","c_v","y_d","f_m","t_i","x_c","f_r","m_t","b_i","b_c","o_f","u_l","r_í","h_g","b_r","a_r","f_s","z_l","u_í","r_ó","b_ñ","m_e","i_p","s_x","o_o","p_t","a_n","f_f","b_í","b_s","g_f","s_e","p_n","d_g","m_z","r_b","u_g","q_í","o_n","d_ñ","f_d","ú_e","a_m","a_é","j_s","z_n","n_v","f_ñ","c_m","l_á","r_u","é_a","d_l","o_u","l_i","d_x","c_ó","c_u","v_l","l_y","v_t","f_b","u_é","ñ_j","x_d","c_e","x_i","i_é","n_o","l_e","b_t","r_é","e_m","p_ó","s_o","x_r","v_g","e_v","b_á","u_o","q_i","o_g","n_ü","g_v","ñ_r","x_v","n_í","k_p","b_o","o_s","m_y","f_u","q_é","l_u","i_á","q_e","a_v","h_a","e_e","c_j","a_z","r_f","d_o","p_j")));

        adjectivesFeatures.add(new Attribute("LemmaLastVC", Arrays.asList("v","c")));
        adjectivesFeatures.add(new Attribute("LemmaLast1VC", Arrays.asList("c","v")));
        adjectivesFeatures.add(new Attribute("LemmaLast2VC", Arrays.asList("v","c","null")));

        adjectivesFeatures.add(new Attribute("LemmaLastBiVC", Arrays.asList("cv","vc","vv","cc")));
        adjectivesFeatures.add(new Attribute("LemmaLastTriVC", Arrays.asList("vcv","cvc","ccv","vvc","cvv","null","vvv","vcc","ccc")));

        adjectivesFeatures.add(new Attribute("LemmaLastSkipBiVC", Arrays.asList("v_v","c_c","c_v","v_c","null")));
        adjectivesFeatures.add(new Attribute("LemmaLast1SkipBiVC", Arrays.asList("c_c","v_v","v_c","c_v","null")));

        adjectivesFeatures.add(new Attribute("LemmaUniGramString", (ArrayList) null));
        adjectivesFeatures.add(new Attribute("LemmaBiGramString", (ArrayList) null));
        adjectivesFeatures.add(new Attribute("LemmaUniGramVCString", (ArrayList) null));
        adjectivesFeatures.add(new Attribute("LemmaBiGramVCString", (ArrayList) null));
        adjectivesFeatures.add(new Attribute("LemmaUniGramOrderString", (ArrayList) null));
        adjectivesFeatures.add(new Attribute("LemmaUniGramReverseOrderString", (ArrayList) null));

        adjectivesFeatures.add(new Attribute("Transition", Arrays.asList("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43")));

        return adjectivesFeatures;
    }
}
