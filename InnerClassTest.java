import  static java.lang.System.*;

public class InnerClassTest {
	public static void main(String [] args){	
	}
}


class Parcel1 {
	// class Contents is  a member of class Parcel1. it means class contents can be qualified with private,public,protected or package access. 
	class Contents {
		private int i =11;
		public int value(){return i;}
	}
	
	class Destination {
		// label cann't  be accessible outside of Destination inner class.  
		private String label;
		// Constructor
		Destination(String whereTo){
			label = whereTo;
		}
		//  readLabel method has package access visibility.
		String readLabel(){return label;}
	}
	
	public void ship(String dest){
		// instantiated objects of Contents, Destination;
		Contents c = new Contents();
		Destination d = new Destination(dest);
		out.println("Going .... " + d.readLabel());
	}
	
	public static void main(String [] args){
		Parcel1 p = new Parcel1();
		p.ship("India");
	}
}

class Parcel2 {
	class Contents {
		private int i =11;
		public int value(){return i;}
	}
	
	class Destination {
		private String label;
		Destination(String whereTo){
			label = whereTo;
		}
		String readLabel(){return label;}
	}
	// Outer class can return a reference of inner class object .
	public Destination to(String dest){
		return new Destination(dest);
	}
	
	public Contents contents(){
		return new Contents();
	}
	
	public void ship(String dest){
		Contents c = new Contents();
		Destination d = new Destination(dest);
		out.println("Going .... " + d.readLabel());
	}
	public static void main(String [] args){
		Parcel2 p = new Parcel2();
		p.ship("India");
		Parcel2 q = new Parcel2();
		//  This is method of creating inner class object . Ex == outClassName.innerClassName referenceName;
		Parcel2.Contents c = q.contents();
		Parcel2.Destination d = q.to("SriLanka");
		out.println("Going .... " + d.readLabel());
	}
}

interface Selector {
	boolean end();
	Object current();
	void next();
}

class Sequence {
	private Object [] items;
	private int next = 0;
	public Sequence(int size){
		items = new Object[size];
	}
	public void add(Object x){
		if(next < items.length){
			items[next++] = x;
		}
	}
	// inner class secretly captures a reference to a particular abject of enclosing class that is responsible for creating it.
	// So whenever you access member of enclosing class that object reference is used.
	private class SequenceSelector implements Selector {
		private int i = 0;
		// inner class can access all the members of outer class without any qualifition. like items in this case.
		public boolean end(){return i == items.length;}
		public Object current(){return items[i];}
		public void next(){if(i < items.length)i++;}
	}
	
	public Selector selector(){
		return new SequenceSelector();
	}
	public static void main(String [] args){
		Sequence sequence = new Sequence(10);
		for(int i = 0 ; i < 10 ;i++){
			sequence.add(Integer.toString(i));
		}
		Selector s  = sequence.selector();
		while(!s.end()){
			out.println(s.current() + " ");
			s.next();
		}
	}
}


class DotThis {
	public void f(){out.println("DotThis.f()");}
	public class Inner {
		public DotThis outer(){
			// If you want to produce reference of outer class object which is link to inner class object, use ClassName.this
			return DotThis.this;
		}
	}
	public Inner inner(){
		return new Inner();
	}
	
	public static void main(String [] args){
		DotThis d = new DotThis();
		DotThis.Inner x = d.inner();
		x.outer().f();
		DotThis d1 = new DotThis();
		//  Without an object of outer class you can  create an object of inner class.
		//  To do so , you have to follow following syntax; OuterClassObject.new InnerClassName() ;
		DotThis.Inner x1 = d1.new Inner();
		x1.outer().f();
	}
}




class Test {
	public static void main(String [] args){
		Parcel1 p = new Parcel1();
		Parcel1.Destination d = p.new Destination("Bali");
		out.println("Going .... " + d.readLabel());
	}
}
