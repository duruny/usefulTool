package btrace;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.println;

/*�����ϵ�ע��
 @OnMethod ����ָ��trace��Ŀ����ͷ����Լ�����λ��, ��ע��ķ�����ƥ��ķ���ִ�е�ָ����λ�ûᱻ���á�"clazz"��������ָ��Ŀ������, ����ָ��ȫ�޶�����, ����"java.awt.Component", Ҳ������������ʽ(���ʽ����д��"//"��, ����"/java\\.awt\\..+/")��"method"��������ָ����trace�ķ���. ���ʽ���Բο��Դ�������(NewComponent.java �� Classload.java, ���ڷ�����ע����Բο�MultiClass.java). ��ʱ��trace����ͷ�������Ҳʹ����ע��. �÷��ο��Դ�����WebServiceTracker.java. ���ע��Ҳ�ǿ���ʹ��������ʽ, ���������"@/com\\.acme\\..+/ "��Ҳ����ͨ��ָ��������ƥ������, ����"+java.lang.Runnable"����ƥ������ʵ����java.lang.Runnable�ӿڵ���. ����ο��Դ�����SubtypeTracer.java��
 @OnTimer ��ʱ����Trace��ʱ�����ָ������λΪ���룬����ο��Դ����� Histogram.java��
 @OnError ��trace�������쳣���ߴ���ʱ����ע��ķ����ᱻִ��. ���ͬһ��trace�ű��������������쳣, ��ע�ⷽ��Ҳ�ᱻִ�С�
 @OnExit ��trace������������exit(int)����(������������trace����)ʱ, ��ע��ķ����ᱻִ��. �ο��Դ�����ProbeExit.java��
 @OnEvent �����ػ�"�ⲿ"btrace client�������¼�, ���簴Ctrl-C �ж�btraceִ��ʱ������ѡ��2�����������¼����ƣ���ִ��ʹ���˸�ע��ķ���, ��ע���valueֵΪ�����¼����ơ�����ο�����HistoOnEvent.java
 @OnLowMemory ���ڴ泬��ĳ���趨ֵ��������ע��ķ���, ����ο�MemAlerter.java
 @OnProbe ʹ���ⲿ�ļ�XML������trace�����Լ������λ�ã�����ο�ʾ��SocketTracker1.java��java.net.socket.xml��
�����ϵ�ע��
 @Self ����ָ����trace������this���ɲο�����AWTEventTracer.java �� AllCalls1.java
 @Return ����ָ����trace�����ķ���ֵ���ɲο�����Classload.java
 @ProbeClassName (since 1.1) ����ָ����trace������, �ɲο�����AllMethods.java
 @ProbeMethodName (since 1.1) ����ָ����trace�ķ�����, �ɲο�����WebServiceTracker.java��
 @TargetInstance (since 1.1) ����ָ����trace�����ڲ������õ���ʵ��, �ɲο�����AllCalls2.java
 @TargetMethodOrField (since 1.1) ����ָ����trace�����ڲ������õķ�����, �ɲο�����AllCalls1.java �� AllCalls2.java��
��ע��ķ�������
 δʹ��ע��ķ�������һ�㶼������������ǩ��ƥ���õ�, ����һ��ͱ�trace�����в������ֵ�˳��һ��. ��������Ҳ������ע�ⷽ������ʹ��, ���һ��������������Ϊ*AnyType[]*, ���������˳��"ͨ��"�������в���. δע�ⷽ����Ҫ��*Location*���ʹ��:
 Kind.ENTRY-��trace��������
 Kind.RETURN- ��trace��������ֵ
 Kind.THROW - ���쳣
 Kind.ARRAY_SET, Kind.ARRAY_GET - ��������
 Kind.CATCH - �����쳣
 Kind.FIELD_SET - ����ֵ
 Kind.LINE - �к�
 Kind.NEW - ����
 Kind.ERROR - ���쳣

�����ϵ�ע��
 @Export ��ע��ľ�̬������Ҫ������jvmstat������������. ʹ�ø�ע��֮��, btrace����Ϳ�����jvmstat�ͻ���(��������ͳ��jvm���е��ڴ�ʹ����)��¶trace�����ִ�д���, ����ɲο�����ThreadCounter.java
 @Property ʹ���˸�ע���trace�ű�����ΪMBean��һ������, һ��ʹ�ø�ע��, trace�ű��ͻᴴ��һ��MBean����MBean������ע��, ����JMX�ͻ��˱���VisualVM, jconsole�Ϳ��Կ�����ЩBTrace MBean. �����Щ��ע��������뱻trace��������Թ���, ��ô�Ϳ���ͨ��VisualVM ��jconsole���鿴��Щ������. ����ɲο�����ThreadCounterBean.java �� HistogramBean.java��
 @TLS ������һ���ű�������һ��ThreadLocal��������. ��ΪThreadLocal�����Ǹ��߳���ص�, һ�����������ͬһ���̵߳������Ƿ�ִ�е��˱�trace�ķ���. ����ɲο�����OnThrow.java �� WebServiceTracker.java
���ϵ�ע��
 @com.sun.btrace.annotations.DTrace ����ָ��btrace�ű�����������ű��е�D���Խű�����, ����ο�����DTraceInline.java.
 @com.sun.btrace.annotations.DTraceRef ����ָ��btrace�ű�����һ��D���Խű��ļ�����. ����ο�����DTraceRefDemo.java.
 @com.sun.btrace.annotations.BTrace ����ָ����java��Ϊһ��btrace�ű��ļ�.*/


/**
 ����Btrace��ѽű��߼�ֱ�����뵽���еĴ����У�������ʹ�������ܶ����ƣ�
 1�����ܴ�������
 2������ʹ������
 3�������׳��򲶻��쳣
 4������ʹ��ѭ��
 5������ʹ��synchronized�ؼ���
 6�����Ժͷ�������ʹ��static����

 ����btrace�ű�����
 btrace [-I <include-path>] [-p <port>] [-cp <classpath>] <pid> <btrace-script> [<args>]
 ʾ��:
 btrace -cp E:\gitProject\ 8836 Tracker.java
 ��������:
 include-pathָ��ͷ�ļ���·�������ڽű�Ԥ�����ܣ���ѡ��
 portָ��BTrace agent�ķ���˼����˿ںţ���������clients��Ĭ��Ϊ2020����ѡ��
 classpath����ָ�������·����Ĭ��Ϊ��ǰ·������ѡ��
 pid��ʾ���̺ţ���ͨ��jps�����ȡ��
 btrace-script��ΪBTrace�ű���btrace�ű������.java��β�����ȱ������ύִ�С���ʹ��btracec����Խű�����Ԥ���롣
 args��BTrace�ű���ѡ�������ڽű��п�ͨ��"��"��"��length"��ȡ������Ϣ��



 �����԰���ʹ�õ�btrace�汾��1.39���ֲ��ɹ��Ŀ��ܸ�btrace�汾�йء�

 ���������ļ�
 ./btrace -o mylog.txt $pid HelloWorld.java
 mylog��������Ӧ�õ�����Ŀ¼��������btrace������Ŀ¼����Σ�ִ�й�һ��-o֮����ִ��btrace����-o Ҳ�����������console��ֱ��Ӧ������Ϊֹ��

 ������ʱҲֱ����ת�����£�
 ./btrace $pid HelloWorld.java > mylog  ת�������·����-o��ͬ��Ҳ�������-o������
 */

/**
 * @author jianpeng.zhang
 * @since 2017/4/11.
 */
@BTrace
public class Tracker
{
    //@TLS ������һ���ű�������һ��ThreadLocal��������
    @TLS
    private static String name;

    // ƥ������Filter�������
    // @OnMethod(clazz="+com.vip.demo.Filter", method="doFilter")

    // ƥ�����и�ע�����ͷ���
    // @OnMethod(clazz="@javax.jws.WebService", method="@javax.jws.WebMethod")

    // @OnMethod(clazz = "/btrace.*/", method = "/.*/")
    // public static void onBar(@ProbeClassName String probeClass, @ProbeMethodName String probeMethod)
    // {
    //     println("entered onBar()");
    //     println(probeClass);    //btrace.MainTrace
    //     println(probeMethod);   //bar
    //     jstack();   // btrace.MainTrace.bar(MainTrace.java) btrace.MainTrace.main(MainTrace.java:14)
    // }

    // //@Returnֻ�е��÷����з���ֵʱ���ã���Ȼ�ᱨ��
    // @OnMethod(clazz = "/btrace.*/", method = "bar", location = @Location(Kind.RETURN))
    // public static void onGetPort( @ProbeMethodName String probeMethod, @Return int returnData, @Duration long duration)
    // {
    //     println(probeMethod);
    //     println(returnData);
    //     println(duration / 1000000); //���� 1,000,000 ���Ǻ���
    // }

    // ���캯��ʱ����
    // @OnMethod(clazz = "/btrace.*/", method = "<init>")
    // public static void init()
    // {
    //     println("init-----");
    // }

    //ʹ��callһ��Ҫ����clazz��method����ΪĬ��Ϊ��
    // @OnMethod(clazz = "/btrace.*/", method = "/.*/",
    //         location = @Location(value = Kind.CALL, clazz = "/.*/", method = "/.*/"))
    // public static void onCall(@ProbeMethodName String probeMethod)
    // {
    //     println(probeMethod);
    // }


    // �쳣ʱ����
    // @OnMethod(clazz = "/btrace.*/", method = "/.*/", location = @Location(Kind.ERROR))
    // public static void onBind(Throwable exception, @Duration long duration)
    // {
    //
    // }

    // ���е�ָ����
    // @OnMethod(clazz = "/btrace.*/", location = @Location(value = Kind.LINE, line = 23))
    // public static void online() {
    //     println(" reach line:23");
    // }

    // ʹ���������࣬Ҫ��classpath
    // // ���þ�̬����ʱ��@Self��Ķ���Ϊ�ա�
    // @OnMethod(clazz="/btrace.*/", method="/.*/")
    // public static void m1(@Self MainTrace trace, @ProbeClassName String probeClass, @ProbeMethodName String probeMethod) { // all calls to the methods with signature "()"
    //     println(trace + "     " + "     " + probeMethod + "         " + probeClass);
    //     // println(trace + "");
    // }


    // �鿴�����������ʲô����.����ִ�еķ����������
    // @OnMethod(clazz = "/btrace.*/", method = "/.*/",
    //         location = @Location(value = Kind.CALL, clazz = "/.*/", method = "/.*/"))
    // public static void m(@Self Object self, @TargetMethodOrField String method,
    //         @ProbeMethodName String probeMethod)
    // { // all calls to the methods with signature "()"
    //     // TargetMethodOrField ������ProbeMethodName����õķ���
    //     println(self + "     " + method  + "     " + probeMethod);
    // }

    // ɸѡ��String�����ķ���
    // @OnMethod(clazz = "/btrace.*/", method = "/.*/",
    //         location = @Location(value = Kind.CALL, clazz = "/.*/", method = "/.*/"))
    // @Sampled(kind = Sampled.Sampler.Adaptive)
    // public static void callWithParam(@Self Object self, @TargetMethodOrField String method,
    //         @ProbeMethodName String probeMethod, String par)
    // { // all calls to the methods with signature "(String param)"
    //     println("=========start==========");
    //     println(par);
    //     println(self + "     " + method  + "     " + probeMethod);//Thread[Thread-152,5,main]     println     run
    //     println("=========end==========");
    // }

    // @OnMethod(clazz = "/btrace.*/", method = "/.*/",
    //         location = @Location(value = Kind.CALL, clazz = "/.*/", method = "/.*/"))
    // public static void n(@Self Object self, @ProbeClassName String pcm, @ProbeMethodName String pmn,
    //         @TargetInstance Object instance, @TargetMethodOrField String method, String text)
    // { // all calls to the methods with signature "(String)"
    //     println("Context: " + pcm + "#" + pmn + "#" + method + " " + instance + " " + text);
    //     //    Context: btrace.MainTrace#foo#println java.io.PrintStream@171ae4ae param406
    // }


    // //�鿴��������ֵ
    // @OnMethod(clazz="/btrace.*/", method="/.*/",
    //         location=@Location(value=Kind.RETURN))
    // public static void o(@Self Object self, @ProbeMethodName String pmn, @Return AnyType args) { // all calls to methods
        // printArray(args);
    //     println(pmn + "       " + args);
    // }
    //
    // // ���� nameΪL0��eventʱ����
    // @OnEvent("L0")
    // public static void setL0() {
    //     // BTraceUtils.setInstrumentationLevel(0);  ����level �������� @OnMethod enableAt=@Level(">=1")
    //     println("event----------");
    // }



    // @OnMethod(clazz = "/btrace.*/", method = "foo")
    // public static void started()
    // {
    //     // E:\gitProject\ʵ��С����\src\btrace>btrace -cp E:\gitProject\ʵ��С����\out\production\ʵ��С����  4888 Tracker.java
    //     println(BTraceUtils.Sys.$length()); //�����ű����������� ��������Ϊ2
    //     println(BTraceUtils.Sys.$(0));  // 4888
    //     println(BTraceUtils.Sys.$(1));  // Tracker.java
    // }

    // //��ʱ����
    // @OnTimer(4000)
    // public static void print() {
    //     println("onTime-------");
    // }


    //�����ı���
    // @OnMethod(clazz = "/btrace.*/", method = "/bar/")
    // public static void onfinalize(@Self MainTrace me)
    // {
    //     Field fdField = field("btrace.MainTrace", "i");
    //     printFields(me);
    //     printFields(get(fdField, me));
    //     println("==========");
    // }

    //�ڴ�������ֵ�Ǵ���
    // @OnLowMemory(pool = "Tenured Gen", threshold = 600000000)
    // public static void onLowMem(MemoryUsage mu)
    // {
    //     println("onLowMem");
    //     println(mu);
    // }


    // //�½�����ʱ���ã�clazzΪ��������
    // @OnMethod(
    //         clazz = "/btrace.*/", // tracking in all classes; can be restricted to specific user classes
    //         method = "/.*/", // tracking in all methods; can be restricted to specific user methods
    //         location = @Location(value = Kind.NEWARRAY, clazz = "int"))
    // public static void onnew(@ProbeClassName String pcn, @ProbeMethodName String pmn, String arrType, int dim)
    // {
    //     println(pcn + "     " + pmn);
    //     println(arrType + "   " + dim); //arrType ��Ӧclazz dimΪά��
    // }



    // @Property
    // public static Profiler swingProfiler = BTraceUtils.Profiling.newProfiler();
    //
    // @OnMethod(clazz="/btrace.*/", method="/.*/")
    // public static void entry(@ProbeMethodName(fqn=true) String probeMethod) {
    //     BTraceUtils.Profiling.recordEntry(swingProfiler, probeMethod);
    // }
    //
    // @OnMethod(clazz="/btrace.*/", method="/.*/", location=@Location(value=Kind.RETURN))
    // public static void exit(@ProbeMethodName(fqn=true) String probeMethod, @Duration long duration) {
    //     BTraceUtils.Profiling.recordExit(swingProfiler, probeMethod, duration);
    // }
    //
    // @OnTimer(5000)
    // public static void timer() {
    //     BTraceUtils.Profiling.printSnapshot("Swing performance profile", swingProfiler);
    // }



    // @OnMethod(clazz="java.lang.System", method="getProperty")
    // public static void onGetProperty(String name) {
    //     println(name);      //nameΪgetProperty�Ĳ���
    //     // call property safely here.
    //     println(BTraceUtils.Sys.Env.property(name));    //���� System.getProperty(name);
    // }

    // //���鸳ֵʱ����
    // @OnMethod(clazz="/btrace.*/", method="/.*/", location = @Location(Kind.ARRAY_SET))
    // public static void onArraySet(@ProbeMethodName String method, @ProbeClassName String className) {
    //     println("onArray set");
    //     println(className + "   " + method);
    // }

    // //����ȡֵʱ����
    // @OnMethod(clazz="/btrace.*/", method="/.*/", location = @Location(Kind.ARRAY_GET))
    // public static void onArraySet(@ProbeMethodName String method, @ProbeClassName String className) {
    //     println("onArray get");
    //     println(className + "   " + method);
    // }

    // //����������ʱ���� clazz �� field ����Ҫ����
    // @OnMethod(clazz="/btrace.*/", method="/.*/",
    //         location = @Location(value=Kind.FIELD_GET, clazz="/.*/", field="j"))
    // public static void onFieldGet(@ProbeMethodName String method, @ProbeClassName String className) {
    //     println("on field get");
    //     println(className + "   " + method);
    // }

    //��������ֵʱ���� clazz �� field ����Ҫ����
    @OnMethod(clazz="/btrace.*/", method="/.*/",
            location = @Location(value=Kind.FIELD_GET, clazz="/.*/", field="j"))
    public static void onFieldSet(@ProbeMethodName String method, @ProbeClassName String className) {
        println("on field set");
        println(className + "   " + method);
    }


}
