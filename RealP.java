public class RealP {
    int RealPage;//实页号
    int VirtualPage;//虚页号
    int State;//状态位，表示该实页是否分配给对应的虚页

    public RealP(){}
    public RealP(int rp,int vp,int state){
        this.RealPage=rp;
        this.VirtualPage=vp;
        this.State=state;
    }
}
