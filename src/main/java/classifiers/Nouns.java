package classifiers;

import org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import java.util.*;

/**
 * Created by Ahmed on 1/22/16.
 */
public class Nouns {
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

    public Nouns() {

    }

    public static String computeGender(String extraNounFeatures) {
        String[] features = extraNounFeatures.split("\\|");
        for (String ext : features) {
            if (ext.contains("gen")) {
                int eq = ext.lastIndexOf("=");
                return ext.substring(eq + 1);
            }
        }
        return null;
    }

    public static String computeNumber(String extraNounFeatures) {
        String[] features = extraNounFeatures.split("\\|");
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
                    if (Nouns.computeGender(extra) != null) {
                        featuresMap.put(Features.GEN, Nouns.computeGender(extra));
                    }
                    break;
                case Num:
                    if (Nouns.computeNumber(extra) != null) {
                        featuresMap.put(Features.Num, Nouns.computeNumber(extra));
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
        Instances nounsDataset = new Instances("Nouns", features, 0);
        nounsDataset.setClassIndex(nounsDataset.numAttributes() - 1);

        Instance instance = new DenseInstance(25);
        nounsDataset.add(instance);

        nounsDataset.instance(index).setValue(0, "n");
        for (Features feature : featuresMap.keySet()) {
            if (!(feature.toString().equals("LemmaUniGramString"))  &&
                    !(feature.toString().equals("LemmaBiGramString")) &&
                    !(feature.toString().equals("LemmaUniGramVCString")) &&
                    !(feature.toString().equals("LemmaBiGramVCString")) &&
                    !(feature.toString().equals("LemmaUniGramOrderString")) &&
                    !(feature.toString().equals("LemmaUniGramReverseOrderString"))) {
                Attribute attribute = nounsDataset.attribute(feature.toString());
                if (attribute.isNumeric()) {
                    nounsDataset.instance(index).setValue(attribute, Integer.parseInt(featuresMap.get(feature)));
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
                        nounsDataset.instance(index).setValue(attribute, featuresMap.get(feature));
                    } else {
                        nounsDataset.instance(index).setMissing(attribute);
                    }
                }
            }
        }

        nounsDataset.instance(index).setValue(18, featuresMap.get(Features.LemmaUniGramString));
        nounsDataset.instance(index).setValue(19, featuresMap.get(Features.LemmaBiGramString));
        nounsDataset.instance(index).setValue(20, featuresMap.get(Features.LemmaUniGramVCString));
        nounsDataset.instance(index).setValue(21, featuresMap.get(Features.LemmaBiGramVCString));
        nounsDataset.instance(index).setValue(22, featuresMap.get(Features.LemmaUniGramOrderString));
        nounsDataset.instance(index).setValue(23, featuresMap.get(Features.LemmaUniGramReverseOrderString));

        //add the instance to instances
        nounsDataset.instance(index).setClassMissing();

        return nounsDataset;
    }


    public static ArrayList<Attribute> generateNounsAttributes() {
        ArrayList<Attribute> nounsFeatures = new ArrayList<Attribute>();

        nounsFeatures.add(new Attribute("Category", Arrays.asList("n")));
        nounsFeatures.add(new Attribute("GEN", Arrays.asList("f","m","c","null")));
        nounsFeatures.add(new Attribute("Num", Arrays.asList("p","s","c","null")));

        nounsFeatures.add(new Attribute("LemmaLength"));

        nounsFeatures.add(new Attribute("LemmaLast", Arrays.asList("a","o","n","e","y","d","s","r","l","z","t","é","i","p","x","í","u","g","b","á","m","k","c","f","j","h","q","ó","ú","w","v")));
        nounsFeatures.add(new Attribute("LemmaLast1", Arrays.asList("v","r","s","ó","d","n","u","t","i","e","g","j","a","m","í","o","z","l","c","ñ","h","é","p","y","f","b","á","x","k","null","ú","w","ü")));
        nounsFeatures.add(new Attribute("LemmaLast2", Arrays.asList("r","o","i","l","a","t","q","n","s","c","d","y","e","u","b","m","v","í","g","ñ","x","p","f","h","z","j","null","k","ú","w","á")));

        nounsFeatures.add(new Attribute("LemmaLastBi", Arrays.asList("va","ro","sa","ón","do","na","ra","de","no","ue","ta","to","te","io","ey","go","jo","ad","me","ís","ía","vo","er","or","za","oy","so","ia","lo","eo","da","al","es","ca","ma","ez","en","re","ga","se","la","mo","ño","ja","it","rd","ho","és","il","ed","ha","po","ce","co","oz","ol","je","ar","yo","in","el","fé","ba","án","is","ña","én","zo","he","pa","mt","az","ur","ío","pe","fo","le","ín","xo","as","ka","ud","ri","bo","ea","fe","ya","té","ip","on","ve","ua","uz","ex","ti","ao","ás","ot","lí","oe","fa","tu","null","ne","uo","us","ng","ax","an","ós","ub","ñe","ús","ie","já","um","ir","iz","ni","nd","ae","be","ay","dá","ok","at","ac","et","pá","ún","né","os","om","ei","uk","bé","ff","lé","fs","ge","oj","tí","sh","bu","id","ul","st","ní","uí","cq","tf","tc","nq","sf","mm","pz","cr","ru","rh","mn","lm","ls","ic","zi","rs","nó","íz","ui","rt","ié","úl","oa","dú","xi","ck","uh","ds","bi","ki","nt","bú","ut","ak","ly","eb","ué","by","am","ts","ai","ns","nú","li","má","sí","rí","ox","úo","wh","nk","lf","úa","gú","rg","ko","mw","mú","ef","tv","jé","ap","ái","op","ow","iv","ag","úd","tó","ch","ks","sc","gs","wn","zz","mé","ik","ys","ij","vd","ft","ss","oc","mp","fá","ov","íl","di","ry","py","tá","ee","üe")));
        nounsFeatures.add(new Attribute("LemmaLastTri", Arrays.asList("rva","oro","isa","lón","ado","ona","tra","lde","ión","rno","que","rta","nto","nte","ido","rio","ato","ley","sgo","ajo","cio","dad","rte","yme","ino","esa","uro","aís","bio","mía","ena","mio","cto","rme","ivo","der","lor","nza","roy","iso","cia","lta","ano","bra","ulo","leo","tor","ida","nal","iva","mes","eno","rca","rma","ema","ero","vez","dio","men","eso","rso","aso","lio","ejo","aza","rre","era","lga","ída","ial","ase","llo","lla","ura","umo","gro","ada","nda","año","ndo","lsa","elo","aja","sor","dor","cit","ord","ñía","cho","ana","uez","sta","are","ica","ama","ria","igo","uso","ora","xto","rés","ral","ter","odo","ste","nía","vil","red","pio","cha","upo","gía","olo","rdo","uda","nce","aro","ono","bro","smo","eda","eto","ico","sto","voz","ita","rde","ñol","ogo","eje","uto","mpo","lar","lso","glo","oyo","asa","nco","fin","ago","tal","ega","rro","nsa","vel","nio","aje","ror","ito","gio","alo","ite","tre","afé","eba","mor","reo","ipo","bol","tro","dez","ima","lma","den","rol","ude","nta","nso","mán","ilo","avo","ués","eza","rto","sis","aña","tar","hén","azo","che","apa","gmt","paz","sur","ate","nes","dra","fío","día","lpe","blo","via","ila","nfo","oca","ira","lle","tel","dín","uta","rza","rón","exo","ría","deo","oto","pra","lto","feo","pto","pas","rra","sma","ika","zgo","ler","rna","tud","ari","ble","lud","bre","tia","jer","ibo","fón","ota","tad","nea","ueo","rco","efe","nés","oso","bor","cía","cta","nor","dal","ire","ior","lza","tmo","aya","ete","lea","fra","val","tía","nel","nar","ngo","rgo","evo","cel","ité","rea","ola","ego","sco","hip","ion","ave","gen","gor","ven","emo","osa","pal","lba","gua","río","mar","gia","seo","luz","null","nti","dro","ñal","nja","opa","ldo","jón","das","gar","ina","sez","mno","vía","ijo","lia","ede","ñor","eña","les","iar","oma","dia","eja","iño","món","aco","ual","cío","usa","ver","ata","sla","ice","ope","nzo","eta","nca","cao","ame","ara","tin","mpa","edo","rzo","pás","udo","cot","amo","gla","lés","dés","elí","clo","iel","rey","ote","plo","dre","oja","sol","ayo","tas","roe","ofa","fil","pel","rín","cer","dea","eño","ber","tío","oño","teo","imo","gán","nón","bía","ñán","uja","gma","eca","ína","eco","pso","cso","ela","aga","zón","ofo","itu","ula","lva","izo","nde","rpo","ído","one","ruo","tus","pón","una","ojo","pez","vio","oga","aca","ing","íso","our","oña","duo","tín","ser","ozo","fax","man","mal","gue","iós","tón","lub","fía","eñe","ene","bús","gra","bal","ras","uía","són","oza","gas","cón","roz","ofe","cie","ien","pie","mia","bar","ajá","lti","hol","lum","ine","uir","niz","ele","xia","vni","son","ujo","ond","ile","riz","gno","dae","abe","oda","day","ndá","ija","boy","dok","jar","cés","mil","til","lan","ray","par","lon","ies","tat","sía","sia","tez","uno","ñac","ale","lao","bón","aor","jet","taz","apá","pía","iga","lén","ubo","lpa","uey","omo","sca","gel","ube","don","tol","etu","lda","tte","dil","rne","mbe","mún","iña","lte","uña","rné","ala","ped","rba","neo","fán","los","cor","rom","pia","ifa","mna","dei","rum","lvo","tis","ceo","lis","dón","spa","cua","buk","pen","uma","lán","lin","per","ris","iba","rda","let","zca","hos","nje","ose","opo","jir","tán","ñón","tén","lía","dén","iza","gre","ebé","gón","spo","obo","iro","gan","mba","pre","iff","ruz","rán","ive","yer","hoy","ise","lmo","oco","vot","ulé","sal","ffs","gol","ket","age","ple","tio","faz","eón","oce","dar","lez","eur","loj","yor","vín","uga","vés","nas","nir","fín","zar","lot","tum","uje","rbo","rer","aos","pei","afo","ezo","bot","rpe","bit","rbe","mma","lfa","ace","rie","bel","diz","ole","bla","tiz","ios","ití","eve","rga","cla","uco","por","ash","rus","bil","ipe","cra","pes","epa","mol","nra","ibu","did","ren","ril","bus","uge","ome","geo","tex","zul","sio","est","zio","uio","vor","fus","sil","aní","quí","las","uca","ncq","ttf","ctc","cis","cnq","iol","ful","fre","mos","tsf","tnq","pin","uel","ilm","eal","non","lgo","coz","uya","cro","ues","ium","els","and","sea","tic","azi","uia","nia","hís","ers","nos","inó","aíz","nga","pan","ipa","zer","del","cal","het","nís","qui","ron","nás","dío","lío","ort","cán","pié","nse","rez","uño","zas","vío","mus","eva","tea","rla","bum","rvo","aúl","roa","slo","ndú","con","fet","cus","uce","axi","vén","fer","ock","lse","hía","ñil","jez","úho","uis","her","dus","max","sed","rir","ián","ain","ese","ade","zal","ros","ias","bia","ume","jeo","zna","mén","cén","tún","ifo","abo","ces","tos","úma","yen","jal","ads","net","uer","ner","rbi","rmo","aki","uzo","int","ebú","but","nis","ños","ide","rpa","ard","fos","hit","res","cas","gna","eak","lly","peo","oil","web","liz","sus","qué","mbo","dum","hes","key","íno","rsa","afa","ere","hín","set","dby","gil","vit","lam","uts","oya","zai","ins","enú","ugo","oli","car","iri","amá","ong","ker","grí","uán","box","cre","upa","lfo","dúo","out","pus","hón","uos","uva","xeo","gwh","aba","bis","ink","olf","sie","íes","mir","uza","tir","rúa","hor","zle","fas","mis","piz","efa","agú","gby","tés","urs","rel","erg","ots","oka","ear","uín","aut","áut","oki","len","sar","eid","fia","pán","dis","cle","emú","oné","lme","hef","pta","ava","sey","uet","ent","ors","sso","rri","tua","oom","gás","ejé","rap","ish","rái","fle","eos","ack","lco","pop","how","afe","mín","die","gle","tiv","gag","sel","aúd","ató","tch","vis","cks","als","isc","bín","egs","own","nic","ret","úor","nds","rin","azz","tle","tto","lín","dos","ier","ius","omé","rar","heo","yés","rik","rja","fan","its","lys","pon","sij","pot","dvd","aft","ape","rds","obe","ili","com","xis","meo","iss","ini","foc","amp","fio","rot","eya","ofá","tov","ías","aíl","adi","zko","kal","kos","sas","iki","beo","got","tuz","tes","oia","pub","ais","rry","eis","voy","aui","zen","miz","ses","abú","gae","try","unk","ppy","sse","sna","xos","cip","ntá","sos","nol","vón","cos","ant","ons","ger","uba","rax","rai","hic","apo","ute","tee","een","cap","lca","rao","oss","rje","lid","sit","lus","eti","güe")));

        nounsFeatures.add(new Attribute("LemmaLastSkipBi", Arrays.asList("r_a","o_o","i_a","l_n","a_o","o_a","t_a","l_e","i_n","r_o","q_e","n_o","n_e","i_o","l_y","s_o","c_o","d_d","r_e","y_e","e_a","u_o","a_s","b_o","m_a","m_o","d_r","l_r","n_a","r_y","c_a","l_a","b_a","l_o","t_r","n_l","m_s","e_o","v_z","d_o","m_n","a_a","í_a","i_l","a_e","u_a","g_o","s_r","c_t","o_d","ñ_a","u_z","s_a","x_o","r_s","r_l","s_e","v_l","r_d","p_o","g_a","ñ_l","e_e","f_n","t_l","r_r","i_e","t_e","a_é","m_r","b_l","t_o","d_z","d_n","u_e","u_s","s_s","h_n","c_e","g_t","p_z","n_s","d_a","f_o","v_a","r_n","p_a","p_s","z_o","t_d","a_i","b_e","l_d","j_r","b_r","n_r","d_l","i_r","f_a","c_l","i_é","h_p","g_n","g_r","v_n","p_l","l_z","null","n_i","j_n","d_s","s_z","ñ_r","l_s","u_l","v_r","o_e","t_n","e_í","d_e","s_l","t_s","f_l","c_r","n_n","ñ_n","z_n","i_u","í_o","p_n","v_o","i_g","o_r","f_x","m_l","g_e","i_s","l_b","b_s","s_n","g_s","c_n","r_z","p_e","a_á","l_i","h_l","l_m","u_r","n_z","x_a","v_i","d_y","n_á","b_y","d_k","c_s","p_r","t_t","t_z","ñ_c","b_n","a_r","j_t","u_y","g_l","e_u","m_e","r_é","p_d","r_m","d_i","b_k","l_t","z_a","h_s","e_é","i_f","y_r","h_y","v_t","u_é","f_s","k_t","f_z","e_n","e_r","l_j","v_s","z_r","t_m","p_i","b_t","i_í","a_h","t_x","z_l","e_t","a_í","q_í","n_q","t_f","c_c","c_q","f_e","t_q","i_m","e_l","c_z","e_s","a_d","t_c","i_ó","a_z","h_t","q_i","o_t","p_é","z_s","b_m","a_l","n_ú","f_t","f_r","o_k","h_a","j_z","ú_o","h_r","m_x","s_d","a_n","j_o","ú_a","y_n","j_l","n_t","r_i","i_t","e_ú","ñ_s","e_k","o_l","w_b","q_é","d_m","k_y","s_t","z_i","o_i","i_i","o_g","k_r","g_í","u_n","b_x","g_h","i_k","o_f","í_s","z_e","a_ú","g_y","e_g","o_s","a_t","á_t","e_d","o_é","h_f","s_y","u_t","o_m","r_p","i_h","a_k","p_p","h_w","t_v","g_g","a_ó","t_h","i_c","o_n","n_c","r_t","ú_r","h_o","y_s","r_k","s_j","p_t","c_m","x_s","f_c","a_p","o_á","k_l","k_s","p_b","v_y","m_z","t_y","u_k","p_y","c_p","r_x","h_c","e_i")));
        nounsFeatures.add(new Attribute("LemmaLast1SkipBi", Arrays.asList("e_v","null","v_s","l_ó","c_d","s_n","s_r","a_d","c_ó","e_n","o_u","e_t","t_d","a_i","c_t","e_g","b_j","i_i","i_a","a_t","p_m","m_n","r_s","t_r","p_í","m_i","o_í","c_n","e_i","o_m","t_v","o_e","a_o","o_z","t_o","m_s","n_i","o_r","c_l","p_e","c_o","d_d","o_a","d_n","n_ó","r_n","a_c","t_m","j_r","g_d","a_e","s_ó","c_s","u_s","u_a","o_i","s_j","n_z","e_r","n_r","r_a","b_s","a_l","s_m","i_r","c_e","o_d","o_s","d_l","t_j","s_o","r_o","r_i","c_r","a_í","e_h","j_e","i_m","u_d","w_r","t_c","r_m","r_d","t_i","t_g","i_o","h_r","g_r","e_é","t_a","u_t","r_t","i_d","o_t","ó_i","r_h","r_p","i_t","e_d","y_d","t_ó","l_m","p_r","i_e","g_n","a_z","f_c","o_n","n_c","l_g","e_p","x_ó","o_l","i_l","p_y","a_p","t_s","p_g","r_g","e_a","e_s","í_e","l_d","l_j","s_d","d_t","m_t","m_r","l_a","c_f","u_b","u_o","m_d","r_e","u_p","f_n","n_d","a_m","g_ñ","e_á","k_l","e_o","n_v","g_é","b_z","p_ñ","a_u","l_n","b_t","x_t","l_z","t_n","o_h","t_p","i_j","v_ó","p_s","n_t","g_ó","r_ó","o_p","e_l","f_m","u_i","b_i","f_l","u_f","b_c","l_r","e_z","d_ó","n_x","n_e","í_d","v_t","z_n","a_a","a_r","n_n","o_k","a_g","l_e","s_t","u_n","i_u","k_r","z_d","d_c","s_i","u_e","c_b","i_ó","m_c","o_ó","n_a","q_e","t_t","n_h","j_f","i_é","í_m","f_t","i_s","i_í","o_o","v_n","b_d","m_ñ","u_y","b_e","d_r","s_a","p_t","s_í","c_a","r_z","r_r","l_t","u_v","á_e","s_l","u_g","i_c","c_i","l_v","a_b","ó_e","e_u","e_e","r_í","a_j","u_ó","p_l","u_z","h_j","l_ñ","u_j","n_ñ","a_ó","c_m","a_s","ñ_n","c_ñ","j_d","h_z","m_á","m_g","v_d","f_s","d_s","i_n","n_é","ó_a","g_l","f_e","g_t","d_z","d_m","r_y","m_z","p_j","é_o","r_l","m_f","g_c","u_ñ","t_z","t_ñ","f_í","s_c","x_c","o_á","a_á","g_j","t_l","h_g","u_l","n_g","s_f","m_l","s_e","e_ó","i_v","s_y","l_s","n_u","y_n","t_u","l_i","v_l","n_j","ñ_r","h_t","p_d","r_b","r_j","r_c","o_c","e_í","m_u","r_ñ","m_o","f_r","k_a","c_u","o_ú","b_l","a_h","g_í","r_f","e_m","x_a","j_j","u_u","k_t","d_j","n_m","o_é","y_o","l_y","n_o","p_a","d_ñ","c_j","d_i","h_l","u_h","s_u","i_g","z_l","b_a","l_o","j_z","p_p","p_c","u_r","ñ_l","m_j","u_m","j_t","ñ_c","i_h","h_c","u_c","n_b","j_m","v_r","z_ñ","ñ_d","a_n","j_l","e_b","g_z","i_z","n_l","g_o","f_f","o_v","i_p","l_c","p_n","d_a","h_n","p_b","s_x","o_j","j_n","m_e","i_á","r_é","s_é","b_b","r_u","l_b","ñ_t","u_í","q_í","o_b","n_í","d_g","f_á","h_d","k_n","í_o","u_é","t_í","o_f","b_r","p_ó","p_i","p_z","g_m","s_g","f_g","b_í","p_o","l_í","g_i","l_l","z_j","a_f","c_p","s_ñ","d_o","c_í","m_m","é_l","m_ó","b_n","v_v","c_z","t_e","x_n","q_i","t_b","o_g","é_e","s_p","s_s","b_ñ","v_j","x_m","z_m","v_z","a_é","v_c","m_p","m_a","g_a","e_c","s_v","d_e","é_n","l_u","f_o","b_ú","t_x","y_s","e_j","y_t","h_y","b_h","p_é","í_a","c_c","m_y","l_p","h_m","e_ú","z_r","y_m","ñ_z","h_p","á_u","e_k","í_p","d_b","x_d","ú_e","f_d","p_u","z_z","h_v","t_f","n_s","ú_i","á_i","j_y","l_á","s_á","y_g","ú_a","g_á","q_é","ú_t","a_v","z_g","h_s","d_f","c_é","d_u","g_ú","m_í","b_g","v_a","r_k","h_a","z_k","h_i","j_c","g_e","c_v","b_o","u_á","t_ú","a_k","l_w","d_v","b_ó","h_e","ü_n","s_b","v_ñ","k_d","u_k","f_j","z_o","h_k","f_a","x_r","v_b","i_b","x_i","z_c","i_ú","í_i","g_s","w_n","é_i","í_c","a_ü")));

        nounsFeatures.add(new Attribute("LemmaLastVC", Arrays.asList("v","c")));
        nounsFeatures.add(new Attribute("LemmaLast1VC", Arrays.asList("c","v","null")));
        nounsFeatures.add(new Attribute("LemmaLast2VC", Arrays.asList("c","v","null")));

        nounsFeatures.add(new Attribute("LemmaLastBiVC", Arrays.asList("cv","vc","vv","cc","null")));
        nounsFeatures.add(new Attribute("LemmaLastTriVC", Arrays.asList("ccv","vcv","cvc","vvc","cvv","vcc","ccc","vvv","null")));

        nounsFeatures.add(new Attribute("LemmaLastSkipBiVC", Arrays.asList("c_v","v_v","c_c","v_c","null")));
        nounsFeatures.add(new Attribute("LemmaLast1SkipBiVC", Arrays.asList("v_c","null","c_c","c_v","v_v")));

        nounsFeatures.add(new Attribute("LemmaUniGramString", (ArrayList) null));
        nounsFeatures.add(new Attribute("LemmaBiGramString", (ArrayList) null));
        nounsFeatures.add(new Attribute("LemmaUniGramVCString", (ArrayList) null));
        nounsFeatures.add(new Attribute("LemmaBiGramVCString", (ArrayList) null));
        nounsFeatures.add(new Attribute("LemmaUniGramOrderString", (ArrayList) null));
        nounsFeatures.add(new Attribute("LemmaUniGramReverseOrderString", (ArrayList) null));

        nounsFeatures.add(new Attribute("Transition", Arrays.asList("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39")));

        return nounsFeatures;
    }
}
