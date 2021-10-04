package com.fei.java.learningjdk.thread;




public class ThreadInterruptDemo {

    /**
     * Tests whether the current thread has been interrupted.  The
     * <i>interrupted status</i> of the thread is cleared by this method.  In
     * other words, if this method were to be called twice in succession, the
     * second call would return false (unless the current thread were
     * interrupted again, after the first call had cleared its interrupted
     * status and before the second call had examined it).
     */
    //根据注释，使用interrupted方法,
    public void interruptedMethod(){
        System.out.println("测试静态interrupted方法，先中断当前线程（main线程），然后调用两次interrupted，结果应该是一个true和一个false");
        Thread.currentThread().interrupt();
        System.out.println(Thread.interrupted());
        System.out.println(Thread.interrupted());
    }


    /**
     * Tests whether this thread has been interrupted.  The <i>interrupted
     * status</i> of the thread is unaffected by this method.
     *
     */
    public void isInterruptedMethod(){
        System.out.println("测试isInterrupted方法，先中断当前线程，然后调用两次isInterrupted，结果应该是2个true，因为不清楚中断标记");
        Thread t = new Thread();
        t.start();
        t.interrupt();
        System.out.println(t.isInterrupted());
        System.out.println(t.isInterrupted());
    }

    /**
    * 通过在线程中判断中断标记，停止线程
    * **/
    public void catchInterruptDemo() throws Exception{
        Thread t = new Thread(()->{
            for(int i=1000; i>0; i--){
                if( Thread.interrupted() == true){
                    System.out.println("i="+i);
                    System.out.println("捕获此线程中断标记，退出");
                    return;
                }
            }
        });

        t.start();
        Thread.sleep(5);
        t.interrupt();
    }



    public static void main(String[] args) throws Exception{
        new ThreadInterruptDemo().interruptedMethod();
        new ThreadInterruptDemo().isInterruptedMethod();
        new ThreadInterruptDemo().catchInterruptDemo();

    }
}
