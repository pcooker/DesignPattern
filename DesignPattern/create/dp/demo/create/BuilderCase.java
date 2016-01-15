package dp.demo.create;

////////////////建造者(生成器)模式： 将对象的构建和表示分离 //////////////////
/*
 * 组成：抽象构建者，具体构建者，产品角色，导演者
 */
abstract class Computer {
	//抽象产品类，封装通用属性和抽象方法
	protected String type;
	protected String cpu;
	protected String ram;
	
	//public abstract String getType(); //由具体类指定
	public abstract String getCpu();
	public abstract void setCpu(String cpu);
	public abstract String getRam();
	public abstract void setRam(String ram);
}
class T100 extends Computer {
	//具体产品类
	public T100(){
		this.type = "T100";
	}
	public String getCpu() {
		return this.cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public String getRam() {
		return this.ram;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}
	
	public String toString() {
		return this.type+" "+this.cpu+" "+this.ram;
	}
	
}
class T200 extends Computer {
	//具体产品类
	public T200(){
		this.type = "T200";
	}
	public String getCpu() {
		return this.cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public String getRam() {
		return this.ram;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}
	public String toString() {
		return this.type+" "+this.cpu+" "+this.ram;
	}
}
interface Builder {
	//重构零件
	public abstract Builder buildCpu();
	public abstract Builder buildRam();
	public abstract Computer getInstance();
}
class T100Builder implements Builder {
	private T100 t100 = new T100();
	public T100Builder(){}
	public Builder buildCpu() {
		t100.setCpu("i3");
		return this;
	}
	public Builder buildRam() {
		t100.setRam("2G");
		return this;
	}
	public T100 getInstance() {
		return t100;
	}
}
class T200Builder implements Builder {
	private T200 t200 = new T200();
	public T200Builder(){}
	public Builder buildCpu() {
		t200.setCpu("i5");
		return this;
	}
	public Builder buildRam() {
		t200.setRam("4G");
		return this;
	}
	public T200 getInstance() {
		return t200;
	}
}
class Director {
	//导演者
	private Builder builder;
	public Director(){}
	public Computer createT100() {
		builder = new T100Builder();
		return builder.buildCpu().buildRam().getInstance();
	}
	public Computer createT200() {
		builder = new T200Builder();
		return builder.buildCpu().buildRam().getInstance();
	}
}

public class BuilderCase {

	public static void main(String[] args) {
		Director dct = new Director();
		T100 t100 = (T100) dct.createT100();
		T200 t200 = (T200) dct.createT200();
		System.out.println(t100.toString());
		System.out.println(t200.toString());

	}

}
