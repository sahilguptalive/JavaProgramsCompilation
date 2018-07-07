public class WaysOfCreatingThread{

    public static final Object obj="";
    public static void main(String[] args){

        System.out.println("Enter Ctrl- C to stop");
       
        //[Change false to true] 
        if(false){
            //Option - 1 : Creating an instance of subclass of Thread
            //and calling start on it. 
            new ExtendedThread("Thread Learning # 1").start();
            
            //Option - 2 : Creating an instance of Thread and passing Runnable
            //Implementation in its constructor. And therafter we can call start()
            //on it.
            new Thread(new NestedRunnable(),"Thread Learning # 2").start();
        }

        //Question 1: How & Why both Options 1 and 2 works. 
        //Answer   : Class Thread implements Runnable Interface.
        //Which has just one abstract method with signature :
        //"public void run();"
        //Now, In thread class, there are multiple ways to instantiate the class
        //Majorly, we can categorise them into two ways: Pasing a runnable
        //implementation or not passing a runnable implementation.
        //Under its own run implementation, Thread checks if any runnable
        //instance has been passed while creating its instance or not. If yes, then
        //it will just execute it. Else it returns without doing anything.
        //So, it becomes important to overide(but not compulsary,since it is not a
        //abstract method) and provide your thread implementation. It is also
        //important to note that Thread class is not an absract class, so it can
        //overiden without any compulsary method overiding. But it will not do
        //anything.
        //For Reference : run() implementation of Thread:
        //  @Override
        //  public void run() {
        //      if (target != null) {
        //          target.run();
        //          }
        //   }

        //Question 2  : Why we should never call run, but always call start method 
        //on thread instance.
        //Answer      : Calling run() on thread instance does not executes the
        //runnable implementation(code inside run of Thread(and its subclass(es))
        //on worker thread. Rather it just executes the 
        //runnable implementation on the calling thread. If calling thread is a
        //main thread, then runnable implemenation would be executed on that
        //thread itself. But when we call start(), then a native thread is
        //created(say pThread using JNI) and run() method is invoked from that
        //native thread. Which means that runnable implementation is executed on
        //a new worker thread, but never on calling thread. 
        //See below code execution, and observe what they print as the thread
        //name in terminal. 
        //[Change false to true]
        if(false){
            new ExtendedThread("Thread Learning # 3").run();
            System.out.println("====== Line Breaker 2 =====");
            new Thread(new NestedRunnable(),"Thread Learning # 4").run();
        }
        //You must have observed 2 things in this example of #3 and #4. First, thread
        //name is being printed as "main" instead of "Thread Learning # 3".
        //Second, "Thread Name:main Nested Runnable" would never be printed.
        //Reason for first has already been answered in QA - 2. But for second,
        //it is important to remember that we have made the Main Thread to
        //sleep. And Since it is sleeping, Nested Runnable will never get a
        //chance to get executed. Moreover, you wil also oberve that even "Line
        //Breaker 2" would also never be executed, since main thread never got a
        //chance to execut it. Main thread would either being used by "TL #3" to
        //print on console or it is bought to sleep by "TL #3".
        

        //Q-4 Can we call start() on same thread instance multipe times.
        //A-  Calling start() will result in an exception
        //"java.lang.IllegalThreadStateException". This exception is thrown by
        //JVM, since thread is no longer in new state, which means it could be
        //in stopped, started, waiting or blocked state. So, if a thread being
        //started is in any state except "New" state, then this exception is
        //thrown.
        //Note: When our main thread crashes due to any exception(like just
        //mentioned java.lang.IllegalThreadStateException), our worker thread
        //does not stops. It continues to execute. To make sure that the thread
        //stops when main thread stops, we need to write a mechanisim to do so.
        //See below code for demo.
        //[Change false to true]
        if(false){
            Thread t5=new ExtendedThread("Thread Learning # 5");
            t5.start();
            t5.start();
        }

        //Q- What are Daemon Threads? How are they different from user
        //threads(Non-Daemon) threads?
        //A- Daemon thread vs User Threads:
        //   1. JVM waits for all normal threads to finish, before it gets
        //   killed. But, if a thread is made a daemon thread, before starting
        //   it, then JVM would not wait for it to get finished. JVM would just
        //   start the thread,and JVM can be killed immediately without waiting
        //   for this particular thread to be finished.
        //   2. 
        //   
        //
        //[Change false to true]
        if(false){
            Thread t6=new ExtendedThread("Thread Learning #6");
            t6.setDaemon(true);
            t6.start();
        }

        if(false){
            Thread t7=new StateAwareThread("Thread Learning # 7",10000);
            Thread t8=new StateAwareThread("Thread Learning # 8");
            sop("\nBefore Starting: States: t7: "+t7.getState()+" t8: "+t8.getState()+"\n");
            t7.start();
            t8.start();
            sop("\nAfter Starting: States: t7: "+t7.getState()+" t8: "+t8.getState()+"\n");
        }

        if(true){
            Thread t9=new ExtendedThread("Thread Learning # 9");
            Thread t10=new ExtendedThread("Thread Learning # 10");
            t9.start();
            
            try{
                t9.join();
            }catch(Exception e){}

            t10.start();
        }
    }
    
    private static void sop(String args){
        System.out.print(args);
    }

    private static class ExtendedThread extends Thread{
        public ExtendedThread(String name){
            super(name);
        }
        public ExtendedThread(){
            super();
        }
        public void run(){
            while(true){
                try{Thread.sleep(1000);}catch(Exception e){e.printStackTrace();};
                System.out.println("Thread Name:"+Thread.currentThread().getName()+" Extended Thread");
            }
        }
    }
    private static class StateAwareThread extends Thread{
        private final long mTime;
        public StateAwareThread(String name,long time){
            super(name);
            mTime=time;
        }
        public StateAwareThread(String name){
            super(name);
            mTime=1000;
        }
        public StateAwareThread(){
            super();
            mTime=1000;
        }
        public void run(){
            while(true){
               synchronized(obj){
                try{
                    Thread.sleep(mTime);
                }catch(Exception e){
                    e.printStackTrace();
                };
                System.out.println("Thread Name:"+Thread.currentThread().getName()
                        +" State Aware Thread"+"State: "
                        +this.getState());
                }
            }
        }
    }
    /*private static class StateAwareRunnable implements Runnable{
        public void run(){
            while(true){
                System.out.println("Thread Name:"+Thread.currentThread().getName()
                        +" State Aware Runnable"+"State: "
                        +Thread.getState());
                try{Thread.sleep(1000);}catch(Exception e){e.printStackTrace();};
            }
        }
    }*/

    private static class NestedRunnable implements Runnable{
            public void run(){
                while(true){
                    try{Thread.sleep(900);}catch(Exception e){};
                    System.out.println("Thread Name:"+Thread.currentThread().getName()+" Nested Runnable");
                }
            }
    }

    private static class DoNothingThread extends Thread{
        public DoNothingThread(final String name){
            super(name);
        }
        public DoNothingThread(){
            super();
        }
        public void run(){
            //do nothing
        }
    }
}
