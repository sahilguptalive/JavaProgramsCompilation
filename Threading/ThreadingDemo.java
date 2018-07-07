public class ThreadingDemo{

    public static void main(String args[]) throws InterruptedException{
   
        sop("\n"+"Hello World");
        //Q- Why would you use Thread.sleep()
        //A- Uses of Sleep
        //      Making a thread sleep, temporarily ceases execution.
        //   1. It is used for pacing, for slowing down te execution or making
        //   something time intervaled. Like if we want to do something after
        //   some pre-decided time. Or we want to repeat ourself after equal
        //   intervals.
        //   2. When the thread is sleeping, it is not consuming any CPU
        //   resources. So sleep makes CPU available for other threads or
        //   applications.
        //   3. It can also be used in very particular case that we are waiting
        //   for other thread to do something whic could be time-bound.


        //Q- What are the different variants of sleep() method
        //A- Thread.sleep() is a static void method. It temperorily ceases the
        //currently executing thread. 
        //Variant 1
        Thread.sleep(100,10);//first paramter is is millisec, and other parameter is in nanosec
        //Variant 2
        Thread.sleep(123);//just one parameter which is in millisec.
        
        
        //Q- How precise and dependable is sleep
        //A- Precision of sleep depends on underlying system clocks ans
        //schedulers. It is not depedenable for time accuracy for the same
        //reason. Moreover some other can interrupt your sleeping thread.
        
        
        //Q- Is it neccessary to put a try/catch block around sleep()
        //A- Yes, We need to catch InterruptedException since some other thread
        //may interrupt the Threads sleep.
        try{
            Thread.sleep(10);
        }catch(InterruptedException ie){
            sop(ie.toString());
        }


        //Q- What are the pausible reasons you will get InterruptedException
        //A- The only possible reason is you were waiting by calling wait(), or
        //sleeping by calling sleep() or joining by calling join(). And some
        //one(or self) has set the "interrupted" flag by calling method
        //"interrupt()".
        

        //Q- Are any locked objects released or notified when a thread which have them is sleeping
        //A- No, sleep does not releases any thread locks. We need to use wait()
        //method for that. sleep() is used for pausing the execution. wait() is
        //used for inter-thread commuication.
       

        //Q- What is interrupt?
        //A- Making a thread interrupted is an indication that it should stop
        //doing what it was doing, and do something else. Interruptting a thread
        //does not effects a thread execution. It is upto the thread runnable
        //developer that what decision he/she takes on getting an interrupt.
        //Some methods are designed to stop doing what they were doing like
        //sleep(). Sleep method just stops and throws a interrupted exception on
        //getting an interrup to make sure thread do something else.
        
       
        //Q- Is interrupt method a static method? How does it work? How does it affect
        //the currently executing thread.
        //interrupt() is a object member method. It is not a static method. 
        //
        //1. If thread is blocked in wait() methods of object class or  join() or  sleep(). Then it
        //will get InterruptedException(), "interrupted" flag would be cleared.
        //
        //2. If thread is doing an I/O oeration upon "interruptible channel". Then channel will be closed 
        //and ClosedByInterruptException will be thrown."interrupted" flag would be set.
        //
        //3. If a thread is blocked in a selector. it would just return. And "interrupted" flag would be set.
        //
        //4. Otherwise just te flag "interrupted" is set. And no effect would be
        //seen on the thread execution. It depends on the developer to check the
        //flag and do something else.
        
        
        //Q- Difference between object.isInterrupted() and Thread.interrupted()
        //A- A thread implementation can check its own interrupted status by
        //calling a static method: "interrupted()". This method returs true if
        //current thread is interrupted else false. This is a single use method.
        //It clears the status of interruption once it is read. This means that
        //when the interrupted thread reads its set flag, then
        //this flag is cleared.
        //
        //On the other hand, if any thread which have the another thread object
        //and calls method "isInterrupted()". Then it return true/false
        //depending on whether the thread has been interrupted or not.
       
        //Q- Can a thread object call "isInterrupted" instead of calling
        //"Thread.interrupted"?
        //A- 

        //Q- Should we contiuously check if thread have been interrupted?
        //A- If we are doing a long operation, then yes we should check our
        //interrupted flag status. Otherwise we may continue to do that long
        //operation which been interrupted by other thread.
        //If we are doing a smaller operations, then it might not be needed.
        
        
        //Q- How can a sleeping thread be interrupted.
        //Q- sleep() method has been designed to stop itself once a thread which
        //has been sleeped is interrupted. So it just stops being in sleep state
        //and throws the exception to notify the thread to do something else.
        //We have to call non-static method "interrupt" for the intended thread
        //to set the "interrupted" flag of thread. If thread would be sleeping,
        //it would just stop doing that.
        
        
        //Q- Can a same thread interrupt itself. How other threads interrupt the thread.
        //A- Yes, a thread can very well interrupt itself. It is permitted.
        //We have to call non-static method "interrupt" for the intended thread
        //to set the "interrupted" flag of thread. If thread would be sleeping,
        //waiting, joining then it would be just interrupted and exception would
        //be thrown. It would just stop doing that thing.
       
        //Q- What if we call interrupt() multiple times?
        //A- Multiple interrupt() may not do much if previous interrupt work is
        //in progress. We should wait for previous interrupt to take into
        //effect. See demo
        
        //Q- Why sleep & yield are static methods, why it is not thread member method?
        //A- 1. Sleep and yield are always called inside the context of thread
        //which is currently running. So it would be just ineffecient to make it
        //a member method.
        //   2. Sleep and yield are decisions which are made by the calling
        //   thread. Giving this handle to others would result in unexpected
        //   errors or results.
        

        demo();
    }

    private static void sop(String arg){
        System.out.println(arg);
    }

    private static void demo(){

        Thread t1 =new Thread(new R1());
        t1.start();
        
        Thread t2 = new Thread(new R2(t1));
        t2.start();

    }


    private static class R2 implements Runnable{

        Thread t1;

        R2(Thread t){
            this.t1 = t;
        }

        public void run(){
            t1.interrupt();
            //if we do not use sleep then second interrupt would not effect the
            //Thread 1. And while loop may continue executing.
            try{    Thread.sleep(1000);}catch(InterruptedException ie){sop(ie.toString());}
            t1.interrupt();
            try{    Thread.sleep(10);}catch(InterruptedException ie){sop(ie.toString());}
            t1.interrupt();
        }
    }

    private static class R1 implements Runnable{
        
        public void run(){
               try{
                    Thread.sleep(1000000);
               }catch(InterruptedException ie){
                    sop(ie.toString());
                }   
                int i=0;
               while(i<Integer.MAX_VALUE){
                     //checking the flag of interruption while doing a long operation
                    if(Thread.interrupted()){
                        sop("is interrupted - while loop R1");
                        break;
                    }
                   i++;
               }


               Thread.currentThread().interrupt();
               if(Thread.interrupted()){
                   sop("Third interrupt. But following check will return false");
                   if(Thread.interrupted()){
                    sop("Third interrupt. Unreachable code.");
                   }else{
                    sop("Third interrupt flag has been cleared."
                            +"Flag clears if Thread static method is invoked to read the flag");
                   }
                }
              

               Thread.currentThread().interrupt();
               if(Thread.currentThread().isInterrupted()){
                   sop("Fourth interrupt. But following check will return true, since flag has not been cleared.");
                   if(Thread.interrupted()){
                    sop("Fouth interrupt. Flag does not clears if object is used to check the status of flag");
                   }else{
                    sop("Fourth interrupt flag has been cleared.");
                   }
               }
        }
    }

    private static void longOperation(){
       int i=0;
       while(i<Integer.MAX_VALUE){
           i++;

         }
    }
}
