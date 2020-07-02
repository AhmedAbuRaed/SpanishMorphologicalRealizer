package realizer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ahmed on 11/17/15.
 */
public class Transactions {
    List<String> list;
    StringBuilder form;
    int opcount;
    int index;

    public Transactions() {
        list = new ArrayList<String>();
        form = new StringBuilder();
        opcount = 0;
        index = 0;
    }

    public String execute(String lemma, String transaction) {
        String tune = transaction.trim();
        String[] operations = tune.split(" ");
        for (String operation : operations) {
            list.add(opcount, operation);
        }

        form.append(lemma);

        for (int i = list.size() - 1; i >= 0; i--) {
            String operation = operations[i];
            final Pattern pattern = Pattern.compile("\\((.+?)\\)");
            final Matcher matcher = pattern.matcher(operation);
            matcher.find();
            String[] info = matcher.group(1).split(",");


            String character = null;
            if (info.length > 1) {
                index = form.length() - Integer.parseInt(info[0]);
                character = info[1];
            } else {
                index = form.length() - Integer.parseInt(info[0]);
            }

            if (operation.startsWith("Insert")) {
                if (index > -1) {
                    form.insert(index, character);
                }
            } else if (operation.startsWith("Replace")) {
                if (index - 1 > -1) {
                    form.replace(index - 1, index, character);
                }
            } else if (operation.startsWith("Delete")) {
                if (index - 1 > -1) {
                    form.delete(index - 1, index);
                }
            }
        }

        return form.toString();
    }

    public static void main(String args[]) {
        Transactions transactions = new Transactions();
        System.out.println(transactions.execute("seguir", "Replace(0,o) Delete(1) Delete(2) Replace(4,i) "));
        //seguir
        //sigo
    }
}
