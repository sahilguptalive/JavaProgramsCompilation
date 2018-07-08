public class ThreadJoin{
    
    static final int MAX_PRINT = 100;

    public static void main(String[] args){
        //join() stops the execution of current thread till the thread on which
        //this call is made dies. So it waits for the thread to die to continuw
        //its own execution.

        //Just like sleep it has variants
        //1. join() 
        // This method makes the current thread waiting for the other thread to
        // die for infinite period or till InterruptedException is thrown. This is like
        // calling join(0);
        //
        //2. join(long ms)
        // This thread makes the current thread waiting for the other thread to
        // die for given ms milliseconds or till InterruptedException is thrown.
        //
        //3. join(long ms,int ns)
        // This thread makes the current thread waiting for the other thread to
        // die for given ms milliseconds and ns nanoseconds or till InterruptedException is thrown.
       
        //Just as mentioned in class "SleepAndInterrupt" join() is one of the
        //methods which needs to be enclosed within try/catch block and
        //"InterruptedException" needs to be handled.

        //Uses of join:
        //1. 
        demo();
        
        //********Regarding daemon functionality***********
        //By default JVM starts a non-daemon thread which calls the method "main".
        //Every child thread inherits the daemon ability from its parent.
        //If parent is daemon then it will be daemon else not.
        //Default set is false for daemon
        
        //Impact on Daemon flag on thread
        //If all currently executing threads are "daemon" then JVM exits and the
        //daemon threads continue to execute.
        //Otherwise, JVM waits for non-daemon threads to finish.
        //see demo(): Thread 4 print statement is not printed since, JVM exits.
        //
        //If thread is non-daemon then main() method may return but the original
        //non-daemon thread which was initiated by JVM, would continue all
        //executing non-daemonn threads. But JVM thread does not waits and exits
        //if all current threads are daemon thread.
        
        sop("*****main thread dies******");
    }


    private static void demo(){
        //Main Thread is not a daemon thread
        sop("is main thread a daemon thread:"+Thread.currentThread().isDaemon());



         Thread t1=new Thread(new R1Even());
         //we have passed instance of t1 in R2Odd to represent a case where we
         //don't want to block the main thread by calling join on it. And this
         //makes main thread immediately free, but Thread 2 would wait for
         //Thread 1 to die. 
         Thread t2=new Thread(new R2Odd(t1));
         t1.start();
         t2.start();
         //Unlike above case, we are blocking the main thread to wait for
         //thread-2 to die before starting thread 3.
         try{t2.join();}catch(InterruptedException ie){sop(ie.toString());}
         Thread t3=new Thread(new RunMultipleOf(3));
         t3.start();

         //In this particular case, we are waiting for all threads to die before
         //closing the main thread. Just to make sure main thread only dies once
         //all threads die.
         try{
            t1.join();
            t2.join();
            t3.join();
         }catch(InterruptedException ie){
            sop(ie.toString());
         }

         Thread t5=new Thread(new VeryLongRunningRunnable());
         t5.setDaemon(true);
         t5.start();

          //Since thread 5 would not be able to die within 100ms so join would
          //return. Main thread can continue its execution with other things.
         try{
            t5.join(100);
            sop(" thread could not be joined within time, so join returns");
         }catch(InterruptedException ie){
            //An exception would be thrown, if someother thread or main thread
            //itself interrupts Main Thread.
            sop(ie.toString());
         }       

        //Thread 4 sop would not be printed since it is daemon thread, and JVM
        //exits without waiting for it be printed.
         Thread t4=new Thread(new RunMultipleOf(4));
         t4.setDaemon(true);
         t4.start();
         boolean toJoinDaemonThread = false;
         //if we make the thread join a daemon thread, then it will behave like a no-daemon thread
         //make this false to test above mentioned functionality and true to
         //make main thread wait for it to die.
         if(toJoinDaemonThread){
            try{t4.join();}catch(InterruptedException ie){sop(ie.toString());};
         }
    }

    private static class R1Even implements java.lang.Runnable{
        
        public void run(){
            sop("\n\n\n\n\nPriting Even");
            int i=2;
            while(i<MAX_PRINT){
               try{Thread.sleep(10);}catch(InterruptedException ie){sop(ie.toString());};     
               sop("R1Even: "+i);
               i+=2;
            }
        }
    }
    
    private static class R2Odd implements java.lang.Runnable{
        
        Thread t;

        R2Odd(Thread t){
            this.t = t;
        }
        
        public void run(){
            try{t.join();}catch(InterruptedException ie){sop(ie.toString());}
            sop("\n\n\n\n\nPriting Odd");
            int i=1;
            while(i<MAX_PRINT){
               try{Thread.sleep(10);}catch(InterruptedException ie){sop(ie.toString());};     
               sop("R2Odd: "+i);
               i+=2;
            }
        }
    }


    private static class RunMultipleOf implements java.lang.Runnable{
       
        int n;
        RunMultipleOf(int n){
            this.n = n;
        }
        public void run(){
            sop("\n\n\n\n\nPriting Multiple of "+n);
            int i=n;
            while(i<MAX_PRINT){
               try{Thread.sleep(10);}catch(InterruptedException ie){sop(ie.toString());};     
               sop("Multiple of "+n+": "+i);
               i+=n;
            }
        }
    }

    private static class VeryLongRunningRunnable implements Runnable{
    
        public void run(){
            sop("Inside  VeryLongRunningRunnable");
            try{Thread.sleep((long)Integer.MAX_VALUE);}catch(InterruptedException ie){sop(ie.toString());}
        }
    }
    
    private static void sop(java.lang.String arg){
        java.lang.System.out.println(arg);
    }
}
