
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 15.02.2016.
 */
public class Calculator implements Runnable{
    List<Integer> data = new ArrayList<>();
    int startingnumber;
    int endingnumber;
    Counter parent;

    public Calculator(Counter parent, int startingnumber, int endingnumber) {
        this.parent = parent;
        this.startingnumber = startingnumber;
        this.endingnumber = endingnumber;
    }

    @Override
    public void run() {
        for (int i=startingnumber; i<=endingnumber; i++){
            boolean notSimple=false;
            for (int j=2;j<i;j++){
                if (i%j==0) notSimple=true;
            }
            if (!notSimple) data.add(i);
        }
        for (Integer in:data){
            parent.addNumber(in);
        }
    }
}
