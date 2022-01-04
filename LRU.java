import java.util.Scanner;
public class LRU {
    int num=0;//物理块数
    int count=0;//缺页数
    VitualP[] initVitual(){
        VitualP vp[]=new VitualP[10];
        int i;
        for(i=0;i<10;i++){
            vp[i]=new VitualP();
            vp[i].VitualPage=i;
            vp[i].RealPage=-1;
            vp[i].State=0;
            vp[i].EnterTime=-1;
            vp[i].AccessTime=-1;
        }
        return vp;
    }

    LinkNode<RealP>  initRealP() {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入分配的物理块数");
        num=sc.nextInt();
        LinkNode<RealP> Lrp=new LinkNode<>();
        for(int i=0;i<num;i++) {
            RealP rp=new RealP();
            rp.RealPage=i;
            rp.VirtualPage=-1;
            rp.State=0;
            Lrp.add(rp);
        }
        return Lrp;
    }

    //判断物理块是否有空
    boolean haveEmpty(LinkNode<RealP> rp){
        Node<RealP> r=rp.head;
        while(r!=null){
            if(r.getDate().State==0)
                return true;
            r=r.next;
        }
        return false;
    }

    public void addRealP(LinkNode<RealP> lrp,VitualP vp[],VitualP v,int time){
        int i,j;
        if(v.State==1) {//若虚页在物理块中
            System.out.println("该虚页"+v.VitualPage+"已在物理块中"+"\t"+nowrp(lrp));
            for(j=0;j<vp.length;j++){
                if(vp[j].VitualPage==v.VitualPage){
                    vp[j].AccessTime=time;
                }
            }
        }
        else {
            Node<RealP> node = new Node<>(null);
            //若物理块有空位
            if(haveEmpty(lrp)) {
                node = lrp.head;
                for (i = 0; i < lrp.size; i++) {
                    if (node.date.State == 0) {
                        node.date.VirtualPage = v.VitualPage;
                        node.date.State = 1;
                        lrp.set(node.date.RealPage, node.date);
                        System.out.println("虚页" + v.VitualPage + "进入物理块" + i + "中"+"\t"+nowrp(lrp));
                        for (j = 0; j < vp.length; j++) {
                            if (vp[j].VitualPage == v.VitualPage) {
                                vp[j].State = 1;
                                vp[j].RealPage = i;
                                vp[j].EnterTime = time;
                                vp[j].AccessTime = time;
                            }
                        }
                        break;
                    }
                    node = node.next;
                }
            }
            //物理块无空位
            else {
                count++;//缺页
                int m = 0;//记录节点位置
                for (i = 0; i < lrp.size; i++) {
                    if (vp[lrp.getDate(m).VirtualPage].AccessTime > vp[lrp.getDate(i).VirtualPage].AccessTime) {
                        m = i;
                    }
                }
                RealP rp=new RealP(m,v.VitualPage,1);
                for(j=0;j<vp.length;j++){//修改虚拟表的状态
                    if(vp[j].VitualPage == lrp.getDate(m).VirtualPage){
                        vp[j].State=0;
                        vp[j].RealPage=-1;
                        break;
                    }
                }
                lrp.set(m,rp);
                System.out.println("置换页面" + v.VitualPage + "进入块" + m + "中"+"\t"+nowrp(lrp));
                for (j = 0; j < vp.length; j++) {
                    if (vp[j].VitualPage == v.VitualPage) {
                        vp[j].State = 1;
                        vp[j].RealPage = i;
                        vp[j].EnterTime = time;
                        vp[j].AccessTime = time;
                        break;
                    }
                }
            }
        }
    }

    public String  nowrp(LinkNode<RealP> rp){
        Node<RealP> n=rp.head;
        String str="\t"+"当前物理块为：";
        while(n!=null) {
            str+=n.getDate().VirtualPage+"\t";
            n=n.next;
        }
        return str;
    }
    public static void main(String args[]){
        int test[]={7,0,1,2,0,3,2,4,2,3,0,3,2,1,2,0,1,7,1};
        int i,j;
        LRU lru=new LRU();
        VitualP[] vp=lru.initVitual();
        LinkNode<RealP> rp=lru.initRealP();

        for(i=0;i<test.length;i++) {
            lru.addRealP(rp,vp,vp[test[i]],i);
//            System.out.printf("%-10s","Vp");
//            for(j=0;j<vp.length;j++) {
//                System.out.print("\t"+vp[j].VitualPage);
//            }
//            System.out.println();
//            System.out.printf("%-10s","rp");
//            for(j=0;j<vp.length;j++) {
//                System.out.print("\t"+vp[j].RealPage);
//            }
//            System.out.println();
//            System.out.printf("%-10s","state");
//            for(j=0;j<vp.length;j++) {
//                System.out.print("\t"+vp[j].State);
//            }
//            System.out.println();
//            System.out.printf("%-10s","accesstime");
//            for(j=0;j<vp.length;j++) {
//                System.out.print("\t"+vp[j].AccessTime);
//            }
//            System.out.println();
//            System.out.printf("%-10s","Entertime");
//            for(j=0;j<vp.length;j++) {
//                System.out.print("\t"+vp[j].EnterTime);
//            }
//            System.out.println();
        }
        System.out.println("共产生"+ lru.count+"次缺页");
        System.out.println("缺页率为"+Float.valueOf((lru.count*100/test.length))/100);
    }
}
