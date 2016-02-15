import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * Created by User on 15.02.2016.
 */
public class Counter {
    int number;
    int processes;
    List<Thread> data = new ArrayList<>();
    private List<Integer> result = new ArrayList<>();

    public void addNumber(int number){
        synchronized (result){
            result.add(number);
        }
    }

    public Counter(int processes, int number) {
        this.processes = processes;
        this.number = number;
    }

    public void calculate(){
        creatingThreads();
        for (Thread th:data) {
            th.start();
        }
        Thread th2 = new Thread(new ResultPrinter(result, data));
        th2.start();
    }

    private void creatingThreads(){
        int areaofcalculating = number/processes;
        int pointer=1;
        int endpointer=0;
        for (int i=0; i<processes; i++){
            endpointer = pointer+areaofcalculating;
            if (endpointer>number) endpointer=number;
            data.add(new Thread(new Calculator(this, pointer,endpointer)));
            pointer=endpointer+1;
        }
    }

    public class ResultPrinter implements Runnable{
        List<Integer> data;
        List<Thread> threads;

        public ResultPrinter(List<Integer> data, List<Thread> threads) {
            this.data = data;
            this.threads = threads;
        }

        @Override
        public void run() {
            System.out.println("Result is:");
            for (Thread th:threads) {
                try {
                    th.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (Integer out:data){
//                data.sort(new Comparator<Integer>() {
//                    @Override
//                    public int compare(Integer o1, Integer o2) {
//                        if (o1<02) return -1;
//                        if (o1>02) return 1;
//                        return 0;
//                    }
//                });
                System.out.println(out);
            }
        }
    }
}
