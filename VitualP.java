import com.sun.org.apache.xml.internal.security.Init;

public class VitualP {
    int VitualPage;//虚页号
    int RealPage;//实页号
    int State;//状态位，表示是否在内存中
    int EnterTime;//进入内存的时间
    int AccessTime;//最近一次访问的时间
//    VitualP(int Vitual, int Real, int State, int EnterT, int AccessT) {
//        this.VitualPage=Vitual;
//        this.RealPage=Real;
//        this.State=State;
//        this.EnterTime=EnterT;
//        this.AccessTime=AccessT;
//    }
}
