/**
 * It is a testing for deadLock.
 * The deadlock gonna be checked with jstack of jvm.
 */
/**
 * Running DeadLockWithThreadPool:
 * >jstack
 * Full thread dump Java HotSpot(TM) 64-Bit Server VM (9+181 mixed mode):

"DestroyJavaVM" #14 prio=5 os_prio=0 tid=0x0000023b345d9800 nid=0x37b4 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"pool-1-thread-2" #13 prio=5 os_prio=0 tid=0x0000023b58138000 nid=0x2368 waiting for monitor entry [0x000000dcdb8ff000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at DeadLockTest.MultiThread.task1(MultiThread.java:26)
        - waiting to lock <0x0000000088e86df8> (a java.lang.Object)
        - locked <0x0000000088e86de8> (a java.lang.Object)
        at DeadLockTest.MultiThread.run(MultiThread.java:45)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@9/ThreadPoolExecutor.java:1167)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@9/ThreadPoolExecutor.java:641)
        at java.lang.Thread.run(java.base@9/Thread.java:844)

"pool-1-thread-1" #12 prio=5 os_prio=0 tid=0x0000023b58137000 nid=0x105c waiting for monitor entry [0x000000dcdb7fe000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at DeadLockTest.MultiThread.task2(MultiThread.java:35)
        - waiting to lock <0x0000000088e86de8> (a java.lang.Object)
        - locked <0x0000000088e86df8> (a java.lang.Object)
        at DeadLockTest.MultiThread.run(MultiThread.java:47)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@9/ThreadPoolExecutor.java:1167)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@9/ThreadPoolExecutor.java:641)
        at java.lang.Thread.run(java.base@9/Thread.java:844)

"Service Thread" #11 daemon prio=9 os_prio=0 tid=0x0000023b58130000 nid=0x377c runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Common-Cleaner" #10 daemon prio=8 os_prio=1 tid=0x0000023b57f7a800 nid=0x1678 in Object.wait() [0x000000dcdb4fe000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
        at java.lang.Object.wait(java.base@9/Native Method)
        - waiting on <0x0000000088fb6bb0> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@9/ReferenceQueue.java:151)
        - waiting to re-lock in wait() <0x0000000088fb6bb0> (a java.lang.ref.ReferenceQueue$Lock)
        at jdk.internal.ref.CleanerImpl.run(java.base@9/CleanerImpl.java:148)
        at java.lang.Thread.run(java.base@9/Thread.java:844)
        at jdk.internal.misc.InnocuousThread.run(java.base@9/InnocuousThread.java:122)

"Sweeper thread" #9 daemon prio=9 os_prio=2 tid=0x0000023b57efb800 nid=0x2bb8 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C1 CompilerThread2" #8 daemon prio=9 os_prio=2 tid=0x0000023b57ee8800 nid=0x2538 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE
   No compile task

"C2 CompilerThread1" #7 daemon prio=9 os_prio=2 tid=0x0000023b57edc800 nid=0xe38 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE
   No compile task

"C2 CompilerThread0" #6 daemon prio=9 os_prio=2 tid=0x0000023b57ed1800 nid=0x1d30 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE
   No compile task

"Attach Listener" #5 daemon prio=5 os_prio=2 tid=0x0000023b57ecf800 nid=0x474 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Signal Dispatcher" #4 daemon prio=9 os_prio=2 tid=0x0000023b57ece800 nid=0x3824 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Finalizer" #3 daemon prio=8 os_prio=1 tid=0x0000023b57e6a000 nid=0x3288 in Object.wait() [0x000000dcdadfe000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(java.base@9/Native Method)
        - waiting on <0x0000000088f0d000> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@9/ReferenceQueue.java:151)
        - waiting to re-lock in wait() <0x0000000088f0d000> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@9/ReferenceQueue.java:172)
        at java.lang.ref.Finalizer$FinalizerThread.run(java.base@9/Finalizer.java:216)

"Reference Handler" #2 daemon prio=10 os_prio=2 tid=0x0000023b57e61000 nid=0x39c0 waiting on condition [0x000000dcdacff000]
   java.lang.Thread.State: RUNNABLE
        at java.lang.ref.Reference.waitForReferencePendingList(java.base@9/Native Method)
        at java.lang.ref.Reference.processPendingReferences(java.base@9/Reference.java:174)
        at java.lang.ref.Reference.access$000(java.base@9/Reference.java:44)
        at java.lang.ref.Reference$ReferenceHandler.run(java.base@9/Reference.java:138)

"VM Thread" os_prio=2 tid=0x0000023b57e59800 nid=0xc24 runnable

"GC Thread#0" os_prio=2 tid=0x0000023b345f3000 nid=0x3a70 runnable

"GC Thread#1" os_prio=2 tid=0x0000023b345f4000 nid=0x13d8 runnable

"GC Thread#2" os_prio=2 tid=0x0000023b345f7800 nid=0x3674 runnable

"GC Thread#3" os_prio=2 tid=0x0000023b345fc800 nid=0x385c runnable

"G1 Main Marker" os_prio=2 tid=0x0000023b34681800 nid=0x574 runnable

"G1 Marker#0" os_prio=2 tid=0x0000023b345ff800 nid=0x734 runnable

"G1 Refine#0" os_prio=2 tid=0x0000023b3460e800 nid=0x2fd0 runnable

"G1 Refine#1" os_prio=2 tid=0x0000023b34608000 nid=0x2554 runnable

"G1 Refine#2" os_prio=2 tid=0x0000023b34605800 nid=0x2904 runnable

"G1 Refine#3" os_prio=2 tid=0x0000023b34604800 nid=0x1d90 runnable

"G1 Young RemSet Sampling" os_prio=2 tid=0x0000023b34610800 nid=0x1840 runnable

"VM Periodic Task Thread" os_prio=2 tid=0x0000023b58131800 nid=0x21a8 waiting on condition

JNI global references: 57


Found one Java-level deadlock:
=============================
"pool-1-thread-2":
  waiting to lock monitor 0x0000023b57e69700 (object 0x0000000088e86df8, a java.lang.Object),
  which is held by "pool-1-thread-1"
"pool-1-thread-1":
  waiting to lock monitor 0x0000023b57e69800 (object 0x0000000088e86de8, a java.lang.Object),
  which is held by "pool-1-thread-2"

Java stack information for the threads listed above:
===================================================
"pool-1-thread-2":
        at DeadLockTest.MultiThread.task1(MultiThread.java:26)
        - waiting to lock <0x0000000088e86df8> (a java.lang.Object)
        - locked <0x0000000088e86de8> (a java.lang.Object)
        at DeadLockTest.MultiThread.run(MultiThread.java:45)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@9/ThreadPoolExecutor.java:1167)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@9/ThreadPoolExecutor.java:641)
        at java.lang.Thread.run(java.base@9/Thread.java:844)
"pool-1-thread-1":
        at DeadLockTest.MultiThread.task2(MultiThread.java:35)
        - waiting to lock <0x0000000088e86de8> (a java.lang.Object)
        - locked <0x0000000088e86df8> (a java.lang.Object)
        at DeadLockTest.MultiThread.run(MultiThread.java:47)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(java.base@9/ThreadPoolExecutor.java:1167)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(java.base@9/ThreadPoolExecutor.java:641)
        at java.lang.Thread.run(java.base@9/Thread.java:844)

Found 1 deadlock.

 * 
 */
/** Running DeadLockWithoutThreadPool:
 * >jstack
 * Full thread dump Java HotSpot(TM) 64-Bit Server VM (9+181 mixed mode):

"DestroyJavaVM" #14 prio=5 os_prio=0 tid=0x000002403899d800 nid=0x2218 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"flag = 1" #13 prio=5 os_prio=0 tid=0x000002405c4b6000 nid=0x3840 waiting for monitor entry [0x0000009e2c3fe000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at DeadLockTest.MultiThread.task1(MultiThread.java:26)
        - waiting to lock <0x0000000088ece388> (a java.lang.Object)
        - locked <0x0000000088ece378> (a java.lang.Object)
        at DeadLockTest.MultiThread.run(MultiThread.java:45)
        at java.lang.Thread.run(java.base@9/Thread.java:844)

"flag = 0" #12 prio=5 os_prio=0 tid=0x000002405c4b5800 nid=0x2108 waiting for monitor entry [0x0000009e2c2ff000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at DeadLockTest.MultiThread.task2(MultiThread.java:35)
        - waiting to lock <0x0000000088ece378> (a java.lang.Object)
        - locked <0x0000000088ece388> (a java.lang.Object)
        at DeadLockTest.MultiThread.run(MultiThread.java:47)
        at java.lang.Thread.run(java.base@9/Thread.java:844)

"Service Thread" #11 daemon prio=9 os_prio=0 tid=0x000002405c4ad800 nid=0x39b4 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Common-Cleaner" #10 daemon prio=8 os_prio=1 tid=0x000002405c30e000 nid=0xdc in Object.wait() [0x0000009e2bfff000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
        at java.lang.Object.wait(java.base@9/Native Method)
        - waiting on <0x0000000088fb6bb0> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@9/ReferenceQueue.java:151)
        - waiting to re-lock in wait() <0x0000000088fb6bb0> (a java.lang.ref.ReferenceQueue$Lock)
        at jdk.internal.ref.CleanerImpl.run(java.base@9/CleanerImpl.java:148)
        at java.lang.Thread.run(java.base@9/Thread.java:844)
        at jdk.internal.misc.InnocuousThread.run(java.base@9/InnocuousThread.java:122)

"Sweeper thread" #9 daemon prio=9 os_prio=2 tid=0x000002405c279000 nid=0x39c4 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C1 CompilerThread2" #8 daemon prio=9 os_prio=2 tid=0x000002405c270000 nid=0x12e4 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE
   No compile task

"C2 CompilerThread1" #7 daemon prio=9 os_prio=2 tid=0x000002405c26c000 nid=0x2fe0 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE
   No compile task

"C2 CompilerThread0" #6 daemon prio=9 os_prio=2 tid=0x000002405c25f800 nid=0x3168 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE
   No compile task

"Attach Listener" #5 daemon prio=5 os_prio=2 tid=0x000002405c256800 nid=0x2ba8 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Signal Dispatcher" #4 daemon prio=9 os_prio=2 tid=0x000002405c255800 nid=0x3a58 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Finalizer" #3 daemon prio=8 os_prio=1 tid=0x000002405c1f1000 nid=0x1094 in Object.wait() [0x0000009e2b8fe000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(java.base@9/Native Method)
        - waiting on <0x0000000088f0d000> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@9/ReferenceQueue.java:151)
        - waiting to re-lock in wait() <0x0000000088f0d000> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(java.base@9/ReferenceQueue.java:172)
        at java.lang.ref.Finalizer$FinalizerThread.run(java.base@9/Finalizer.java:216)

"Reference Handler" #2 daemon prio=10 os_prio=2 tid=0x000002405c1e7000 nid=0xe0c waiting on condition [0x0000009e2b7ff000]
   java.lang.Thread.State: RUNNABLE
        at java.lang.ref.Reference.waitForReferencePendingList(java.base@9/Native Method)
        at java.lang.ref.Reference.processPendingReferences(java.base@9/Reference.java:174)
        at java.lang.ref.Reference.access$000(java.base@9/Reference.java:44)
        at java.lang.ref.Reference$ReferenceHandler.run(java.base@9/Reference.java:138)

"VM Thread" os_prio=2 tid=0x000002405c1e1800 nid=0x3510 runnable

"GC Thread#0" os_prio=2 tid=0x00000240389b5800 nid=0x35fc runnable

"GC Thread#1" os_prio=2 tid=0x00000240389b7000 nid=0x288c runnable

"GC Thread#2" os_prio=2 tid=0x00000240389b9800 nid=0x1110 runnable

"GC Thread#3" os_prio=2 tid=0x00000240389bb800 nid=0x2700 runnable

"G1 Main Marker" os_prio=2 tid=0x0000024038a3a000 nid=0x18a0 runnable

"G1 Marker#0" os_prio=2 tid=0x0000024038a3c000 nid=0xd14 runnable

"G1 Refine#0" os_prio=2 tid=0x00000240389cb800 nid=0xbb0 runnable

"G1 Refine#1" os_prio=2 tid=0x00000240389ca800 nid=0x3398 runnable

"G1 Refine#2" os_prio=2 tid=0x00000240389c4800 nid=0x16dc runnable

"G1 Refine#3" os_prio=2 tid=0x00000240389c0800 nid=0x36c4 runnable

"G1 Young RemSet Sampling" os_prio=2 tid=0x00000240389cd000 nid=0x3828 runnable

"VM Periodic Task Thread" os_prio=2 tid=0x000002405c4b0000 nid=0x55c waiting on condition

JNI global references: 6


Found one Java-level deadlock:
=============================
"flag = 1":
  waiting to lock monitor 0x000002405c1f0880 (object 0x0000000088ece388, a java.lang.Object),
  which is held by "flag = 0"
"flag = 0":
  waiting to lock monitor 0x000002405c1f0980 (object 0x0000000088ece378, a java.lang.Object),
  which is held by "flag = 1"

Java stack information for the threads listed above:
===================================================
"flag = 1":
        at DeadLockTest.MultiThread.task1(MultiThread.java:26)
        - waiting to lock <0x0000000088ece388> (a java.lang.Object)
        - locked <0x0000000088ece378> (a java.lang.Object)
        at DeadLockTest.MultiThread.run(MultiThread.java:45)
        at java.lang.Thread.run(java.base@9/Thread.java:844)
"flag = 0":
        at DeadLockTest.MultiThread.task2(MultiThread.java:35)
        - waiting to lock <0x0000000088ece378> (a java.lang.Object)
        - locked <0x0000000088ece388> (a java.lang.Object)
        at DeadLockTest.MultiThread.run(MultiThread.java:47)
        at java.lang.Thread.run(java.base@9/Thread.java:844)

Found 1 deadlock.

 * 
 */
/**
 * @author SamJ
 *
 */
package Mytraining.Thread.DeadLockTest;