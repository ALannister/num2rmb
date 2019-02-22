
public class Num2Rmb

{

private String[] hanArr = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};

private String[] unitArr= {"仟","佰","十","亿","仟","佰","十","万","仟","佰","十","元"};

private String[] unitArr3= {"零仟","零佰","零十","零亿","零仟","零佰","零十","零万","零仟","零佰","零十","零元"};

private String[] unitArr4= {"零","零","零","亿","零","零","零","万","零","零","零","元"};

private String[] unitArr2={"角","分","整"};

 

/**

* 该方法将给定的浮点数分割成整数部分和小数部分

* @param num 该参数指定需要分割的浮点数

* @return 返回字符串数组，包含整数部分和小数部分

*/

private String[] divide(double num)

{

long zheng = (long)num;

long xiao   = Math.round((num-zheng)*100);//此处需要四舍五入

return new String[]{zheng+"",xiao+""};

}

 

/**

* 该方法将给定的整数部分字符串转换为人民币读法字符串

* @param zheng 该参数指定需要转换的整数部分字符串

* @return 返回字符串，整数部分的人民币读法

*/

//将整数部分每四个数分为一组，每个组的读法基本相同，每个组最多4个0，按0的个数分类讨论，考虑所有情况

private String zheng2HanStr(String zheng)

{

System.out.println("整数部分："+zheng);

String result = "";

int length = zheng.length();

for(int i=0;i<length;i++)

{

int num = zheng.charAt(i)-48;//将数字字符换成对应数字

result += hanArr[num]+unitArr[12-length+i];//将数字换成汉字并加上数位

}//end for

System.out.println(result);//"叁佰零十贰亿玖仟零佰零十零万零仟零佰零十零元"

length = result.length();

for(int i=0;i<12;i++)

{

result = result.replaceAll(unitArr3[i],unitArr4[i]);

}//end for

System.out.println(result);//"叁佰零贰亿玖仟零零万零零零元"

while(result.indexOf("零零")>=0)//去掉所有"零零"

{

result = result.replaceAll("零零","零");

}//end while

System.out.println(result);//"叁佰零贰亿玖仟零万零元"

result = result.replaceAll("零万","");

System.out.println(result);//"叁佰零贰亿玖仟零元"

result = result.replaceAll("零元","元");

System.out.println(result);//"叁佰零贰亿玖仟元"

return result;

}

 

/**

* 该方法将给定的小数部分字符串转换为人民币读法字符串

* @param xiao 该参数指定需要转换的小数部分字符串

* @return 返回字符串，小数部分的人民币读法

*/

private String xiao2HanStr(String xiao)

{

System.out.println("小数部分："+xiao);

String result = "";

int length = xiao.length();

if(length == 2){

int num1 = xiao.charAt(0)-48;

int num2 = xiao.charAt(1)-48;

if(num1 != 0 && num2 ==0) {result += hanArr[num1]+unitArr2[0];}//针对小数部分是".90"这种情况

else  {result += hanArr[num1]+unitArr2[0]+hanArr[num2]+unitArr2[1];}//针对小数部分是".89"这种情况

}

else if(length == 1){

int num1 = xiao.charAt(0)-48;

if(num1 != 0) {result += hanArr[0]+hanArr[num1]+unitArr2[1];}//针对小数部分是".09"这种情况

else {result += unitArr2[2];}//针对小数部分是".00"这种情况

System.out.println("有一位！");

}

else{//针对可能出现的意外情况

result += unitArr2[2];

System.out.println("没有位！");

}//end if

return result;

}

 

/**

* 该方法将给定的浮点数转换为人民币读法字符串

* @param num 该参数指定需要转换的浮点数

* @return 返回字符串，浮点数的人民币读法

*/

private String toHanStr(double num)

{

String result = "";

Num2Rmb nr2 = new Num2Rmb();

String[] diviStr2 = nr2.divide(num);

result += nr2.zheng2HanStr(diviStr2[0]);

result += nr2.xiao2HanStr(diviStr2[1]);

return result;

}

 

public static void main(String[] args)

{

Num2Rmb nr = new Num2Rmb();

/*

System.out.println(nr.toHanStr(2036711123.10));

System.out.println(nr.toHanStr(236711123.05));

System.out.println(nr.toHanStr(236711123.00));

System.out.println(nr.toHanStr(236711123.89));

System.out.println(nr.toHanStr(230000023L));

System.out.println(nr.toHanStr(30230000000L));//30230000000默认是int类型，超过表数范围

System.out.println(nr.toHanStr(30230000000.0));

*/

System.out.println(nr.toHanStr(30290000000.09));

} 

}
