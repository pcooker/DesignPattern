package dp.demo.create;

/*
 * ����ģʽ���򵥹������������������󹤳�ģʽ
 * �����Ĳ��֣� ����������������󣬾���Ĺ�����������Ķ���ʵ��
 */

//////////////////////////// �򵥹���ģʽ ////////////////////////////////////////
interface Car {
	//��������
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

/////////////////////////// ��������ģʽ   /////////////////////////////////////////
interface Product {
	//�����Ʒ
	//����һЩ�����ĳ��󷽷�
	public abstract void method1();
	public abstract void method2();
}
interface Creator {
	//��������
	public abstract <T extends Product> T create(Class<T> c);
}
class ConcreteProduct implements Product {
	//�����Ʒ
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
	//����Product�ľ��幹����
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
//////////////////////////////////԰��--ˮ��/////////////////////////////////////////
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
///////////////���󹤳��� �ǹ�������ģʽ�������棬��Ե��ǹ����غͲ�Ʒ��////////////////
/*
 * ���󹤳������幤���������Ʒ�������Ʒ
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
	public abstract ProductA factoryA(); //��Ʒϵ��A
	public abstract ProductB factoryB(); //��Ʒϵ��B
}
class Factory1 implements Factory {
	public ProductA factoryA() {
		//����1ֻ�����ȼ�Ϊ1�Ĳ�Ʒ
		return new ProductA1();
	}
	public ProductB factoryB() {
		//return new ProductB1();
		return null;
	}
}
class Factory2 implements Factory {
	public ProductA factoryA() {
		//����1ֻ�����ȼ�Ϊ1�Ĳ�Ʒ
		return new ProductA2();
	}
	public ProductB factoryB() {
		//return new ProductB2();
		return null;
	}
}

public class FactoryCase {

	public static void main(String[] args) {
		//�򵥹���ģʽ
		Car car = Driver.create("Bwm");
		car.DriveCar();
		
		//��������ģʽ
		Gardener c = new AppleGardener();
		Friut f = c.create();
		f.harvest();
		
		//���󹤳�ģʽ
		Factory factory1 = new Factory1();
		Factory factory2 = new Factory2();
		ProductA A1 = factory1.factoryA();
		ProductA A2 = factory2.factoryA();
		A1.method1();
		A2.method1();
		
	}
}
