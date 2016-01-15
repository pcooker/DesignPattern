package dp.demo.create;

/*
 * 工厂模式：简单工厂，工厂方法，抽象工厂模式
 * 包含四部分： 抽象构造器，抽象对象，具体的构造器，具体的对象实现
 */

//////////////////////////// 简单工厂模式 ////////////////////////////////////////
interface Car {
	//公共方法
	public abstract void DriveCar();
}
class BenzCar implements Car {
	public void DriveCar() {
		System.out.println("Drive Benz");
	}
}
class BwmCar implements Car {
	public void DriveCar() {
		System.out.println("Drive Bwm");
	}
}
class Driver {
	public static Car create(String type) {
		if("Benz".equalsIgnoreCase(type)) {
			return new BenzCar();
		}
		else if("Bwm".equalsIgnoreCase(type)) {
			return new BwmCar();
		}
		return null;
	}
}

/////////////////////////// 工厂方法模式   /////////////////////////////////////////
interface Product {
	//抽象产品
	//定义一些公共的抽象方法
	public abstract void method1();
	public abstract void method2();
}
interface Creator {
	//抽象构造器
	public abstract <T extends Product> T create(Class<T> c);
}
class ConcreteProduct implements Product {
	//具体产品
	private boolean flag;
	public ConcreteProduct() {
		flag = true;
	}
	public void method1(){
		//...
		System.out.println(this.getClass().getName()+":method1:"+flag);
	}
	public void method2() {
		//...
		System.out.println(this.getClass().getName()+":method2:"+flag);
	}
}
class ConcreteCreator implements Creator {
	//创建Product的具体构造器
	@SuppressWarnings("unchecked")
	public <T extends Product> T create(Class<T> c) {
		Product product = null;
		try{
			product = (Product) Class.forName(c.getName()).newInstance();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return (T) product;
	}
}
//////////////////////////////////园丁--水果/////////////////////////////////////////
interface Friut {
	public abstract void grow();
	public abstract void harvest();
}
interface Gardener {
	public abstract <T extends Friut> T create();
}
class Apple implements Friut {
	private int age;
	public Apple(){
		age = 0;
	}
	public void grow() {
		System.out.println(this.getClass().getName()+"grow..."+age);
	}
	public void harvest() {
		System.out.println(this.getClass().getName()+"harvest..."+age);
	}
}
class Grape implements Friut {
	private int age;
	public Grape(){
		age = 0;
	}
	public void grow() {
		System.out.println(this.getClass().getName()+"grow..."+age);
	}
	public void harvest() {
		System.out.println(this.getClass().getName()+"harvest..."+age);
	}
}
class AppleGardener implements Gardener {
	@SuppressWarnings("unchecked")
	public Apple create() {
		return new Apple();
	}
}
class GrapeGardener implements Gardener {
	@SuppressWarnings("unchecked")
	public Grape create() {
		return new Grape();
	}
}
///////////////抽象工厂： 是工厂方法模式的升级版，针对的是工厂簇和产品簇////////////////
/*
 * 抽象工厂，具体工厂，抽象产品，具体产品
 */
interface ProductA {
	public abstract void method1();
	public abstract void method2();
}
interface ProductB {
	public abstract void method1();
	public abstract void method2();
}
class ProductA1 implements ProductA {
	public void method1() {
		System.out.println(this.getClass().getName()+"...out");
	}
	public void method2() {
		
	}
}
class ProductA2 implements ProductA {
	public void method1() {
		System.out.println(this.getClass().getName()+"...out");
	}
	public void method2() {
		
	}
}
interface Factory {
	public abstract ProductA factoryA(); //产品系列A
	public abstract ProductB factoryB(); //产品系列B
}
class Factory1 implements Factory {
	public ProductA factoryA() {
		//工厂1只生产等级为1的产品
		return new ProductA1();
	}
	public ProductB factoryB() {
		//return new ProductB1();
		return null;
	}
}
class Factory2 implements Factory {
	public ProductA factoryA() {
		//工厂1只生产等级为1的产品
		return new ProductA2();
	}
	public ProductB factoryB() {
		//return new ProductB2();
		return null;
	}
}

public class FactoryCase {

	public static void main(String[] args) {
		//简单工厂模式
		Car car = Driver.create("Bwm");
		car.DriveCar();
		
		//工厂方法模式
		Gardener c = new AppleGardener();
		Friut f = c.create();
		f.harvest();
		
		//抽象工厂模式
		Factory factory1 = new Factory1();
		Factory factory2 = new Factory2();
		ProductA A1 = factory1.factoryA();
		ProductA A2 = factory2.factoryA();
		A1.method1();
		A2.method1();
		
	}
}
