import static java.lang.System.*;
import java.util.*;
import typeinfo.pets.*;

public class HoldingObjects {
	public static void main(String [] args){
    }
}

class Apple {
	private static long counter;
	private final long id = counter++;
	public long Uid (){
		return id;
	}
}


class Orange {}

class GrannySmith extends Apple{}
class Gala extends Apple{}
class Fuji extends Apple{}
class BraeBurn extends Apple{}


class AppleAndOrangeWithoutGenerics {
	@SuppressWarnings("unchecked")
	public static void main(String [] args){
		ArrayList apples = new ArrayList();
		for(int  i = 0; i < 4 ;i++){
			apples.add(new Apple());
		}
		//apples.add(new Orange());
		for(int  i = 0 ; i < apples.size();i++){
			out.println(((Apple)apples.get(i)).Uid());
		}
		
		// with Generics 
		ArrayList<Apple> apples2 = new ArrayList<Apple>();
		for(int  i = 0 ; i < 3 ;i++){
			apples2.add(new Apple());
		}
		//apples2.add(new Orange());
		
		for(Apple c : apples2){
			out.println(c.Uid());
		}
		// you can also store the devired class object in base class container. compiler use upcasting to precess this request.
		ArrayList<Apple> apples3 = new ArrayList<Apple>();
		apples3.add(new GrannySmith());
		apples3.add(new Gala());
		apples3.add(new Fuji());
		apples3.add(new BraeBurn());
		for(Apple c : apples3){
			// this will print ClasssName followed by unsigned hexadecimal presentation of hash code of an object;
			out.println(c);
		}
		
	}
}


class SampleCollection {
	public static void main(String [] args){
		Collection<Integer> c = new ArrayList<Integer>();
		for(int  i =0 ; i < 10 ;i++)
			c.add(i); // AutoBoxing
		
		for(Integer i : c)
		{	
			out.print(i+" ");
		}
	}
}


// Set 
// 1. Set is an interface which extends Collection. it is an unordered collection of objects in which duplicate values cann't be stored.
//2. Basically Set is implemented by HashSet, LinkedSet and TreeSet.
//3. Set has various methods like add,remove,clear,size etc to enhance the uses of interface.

class SetExample {
	public static void main(String [] args){
		Set<String> hash_set = new HashSet<String>();
		hash_set.add("Geeks");
		hash_set.add("for");
		hash_set.add("Geeks");
		hash_set.add("Example");
		hash_set.add("Set");
		out.println("Set has not duplicate elements");
		out.println(hash_set);
		// TreeSet stores the element in sorted order without duplicate.
		Set<String> tree_set = new TreeSet<String>(hash_set);
		out.println(tree_set);
	}
}


class AddingGroups {
	public static void main(String [] args){
		Collection<Integer> collection = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
		Integer [] moreInts = {6,7,8,9,10};
		// You can add an array into collection directly. because Collection.addAll take only one argument of type List. 
		//!colloection.addAll(moreInts);
		collection.addAll(Arrays.asList(moreInts));
		// Collections is seperate class which is used to create a collection. it has method Collections.addAll which take collection name and varages as arguments.
		Collections.addAll(collection,11,12,13,14,15);
		Collections.addAll(collection,moreInts);
		
		List<Integer> list = Arrays.asList(11,12,13,14,15);
		// set method will replace an value at specified place. 
		list.set(1,99);
		out.println(list);
		// at run time , below statement will throw an error because list is implement from an array. And you cann't resize an array.
		//!list.add(90);
	}
}



class Snow{}
class Powder extends Snow{}
class Heavy extends Powder{}
class Light extends Powder{}
class Crusty extends Snow{} 
class Slush extends Snow{}

class AsListInference{
	public static void main(String [] args){
		List<Snow> snow1 = Arrays.asList(new Powder(),new Crusty(),new Slush());
		// This is not possible because List return by asList method is of type List<Powder>  and you can assign it a referece of type List<Snow>.
		//!List<Snow> snow2 = Arrays.asList(new Light(),new Heavy());
		out.println(snow1);
		// Creation of an empty list.
		List<Snow> snow3 = new ArrayList<Snow>();
		out.println(snow3);
		// It will give incompatible type error because  Collections.addAll take first argument as List. it will perform upcasting for each arguments.  
		Collections.addAll(snow3,new Light(),new Heavy());
		out.println(snow3);
		// below statement , we are specifying the type of arguments whatever we are passing to Arrays.asList method. So resulting List reference type would be List<Snow>
		List<Snow> snow4 = Arrays.<Snow>asList(new Light(),new Heavy());
		out.println(snow4);
	}
}


class PrintingContainers {
	public static Collection fill(Collection<String> collection){
		collection.add("rat");
		collection.add("cat");
		collection.add("dog");
		collection.add("dog");
		return collection;
	}
	
	public static Map fill(Map<String,String> map){
		map.put("rat","Fuzzy");
		map.put("cat","Rags");
		map.put("dog","Bosco");
		map.put("dog","Spot");
		return map;
	}
	
	public static void main(String [] args){
		out.println(fill(new ArrayList<String>()));
		out.println(fill(new LinkedList<String>()));
		out.println(fill(new HashSet<String>()));
		out.println(fill(new TreeSet<String>()));
		out.println(fill(new LinkedHashSet<String>()));
		out.println(fill(new HashMap<String,String>()));
		out.println(fill(new TreeMap<String,String>()));
		out.println(fill(new LinkedHashMap<String,String>()));
	}	
}


class ListFeatures{
	public static void main(String [] args){
		Random rand = new Random(47);
		// Pets class has method called arrayList which will return ArrayList filled with randomly selected Pet object.
		List<Pet> pets = Pets.arrayList(7);
		out.println("1: " + pets);
		Hamster h = new Hamster();
		pets.add(h); // automatically resizes
		out.println("2: " + pets);
		out.println("3: " + pets.contains(h)); // contains method of ArrayList check whether this object present or not in ArrayList.
		pets.remove(h); // remove by object;
		Pet p = pets.get(2); // get()  method of ArrayList class return a object present at index 2; 
		out.println("4: " + p + " " + pets.indexOf(p));
		Pet cymric = new Cymric();
		out.println("5: " + pets.indexOf(cymric)); // here it will return -1 because reference cymric is not present in the ArrayList.
		out.println("6: " + pets.remove(cymric)); //  return false bacuase it doesn't contain the reference cymric in the ArrayList
		// Must be an exact object
		out.println("7: " + pets.remove(p));
		out.println("8: " + pets);
		pets.add(3,new Mouse()); // inserting at index 3. if there is element at 3rd index then it will move all the elements by 1 to right .
		out.println("9: " + pets);
		List<Pet> sub = pets.subList(1,4);
		out.println("subList: " + sub);
		out.println("10: " + pets.containsAll(sub)); // return true if all elements of sub ArrayList is present in  ArrayList pets;
		Collections.sort(sub); // in place sort;
		out.println("sorted subList: " + sub);
		// order is not important for containsAll
		out.println("11: " + pets.containsAll(sub));
		Collections.shuffle(sub,rand);
		out.println("Shuffled subList " + sub);
		out.println("12: " + pets.containsAll(sub));
		List<Pet> copy = new ArrayList<Pet>(pets);
		sub = Arrays.asList(pets.get(1),pets.get(4));
		out.println("sub: " + sub);
		copy.retainAll(sub);
		out.println("13: " + copy);
		copy = new ArrayList<Pet>(pets); // fresh copy
		copy.remove(2); // remove by index
		out.println("14: " + copy);
		copy.removeAll(sub);
		out.println("15: " + copy);
		copy.set(1,new Mouse()); // replacing an element at index 1
		out.println("16: " + copy);
		copy.addAll(2,sub);
		out.println("17: " + copy);
		out.println("18: " + pets.isEmpty());
		pets.clear(); // removing all elements
		out.println("19: " + pets);
		out.println("20: " + pets.isEmpty());
		pets.addAll(Pets.arrayList(4));
		out.println("21: " + pets);
		Object [] o = pets.toArray();
		out.println("21: " + o[3]);
		Pet [] pa = pets.toArray(new Pet[0]);
		out.println("21: " + pa[3].id());
	}
}


class SimpleIteration {
	public static void main(String [] args){
		List<Pet> pets = Pets.arrayList(12);
		// Iterator is an interface which is implemented by each collection like List , set , Queue.
		// Whenever you need a Iterator of colllection type,  you can use iterator method to get it.
		Iterator<Pet> it = pets.iterator();
		// in java , Iterator can move only in one direction. forward;
		// hasNext()  will tell you if there is any object in sequence.
		while(it.hasNext()){
			// next()  return next object in the sequence .
			Pet p = it.next();
			out.print(p.id() + " : " + p + " ");
		}
		out.println();
		for(Pet x : pets)
			out.print(x.id() + " : " + x + " ");
		out.println();
		
		it = pets.iterator();
		for(int  i = 0 ; i < 6 ;i++){
			it.next();
			// remove method removes the object from the sequence.
			it.remove();
		}
		out.println(pets);
	}
}


class CrossContainerIteration {
	// iterator design pattern   :  Client program will call display function by passing iterator without know the underlying collection.
	public static void display(Iterator<Pet> it){
		while(it.hasNext()){
			Pet p = it.next();
			out.print(p.id() + " : " + p  + " ");
		}
		out.println();
	}
	
	public static void main(String [] args){
		ArrayList<Pet> pets = Pets.arrayList(10);
		LinkedList<Pet> petsLL = new LinkedList<Pet>(pets);
		HashSet<Pet> petsHS = new HashSet<Pet>(pets);
		TreeSet<Pet> petsTS = new TreeSet<Pet>(pets);
		display(pets.iterator());
		display(petsLL.iterator());
		display(petsHS.iterator());
		display(petsTS.iterator());
	}
}


class ListIteration {
	public static void main(String [] args){
		List<Pet> pets = Pets.arrayList(8);
		// ListIterator is powerful than Iterator. it extends Iterator interface.
		// its a bidirectional iterator.
		ListIterator<Pet> it = pets.listIterator();
		while(it.hasNext()){
			// ListIterator  has also next() ,previous() ,nextIndex() ,previousIndex()  methods;
			out.print(it.next() + " , " + it.nextIndex() + " , " + it.previousIndex() + " :");
		}
		out.println();
		while(it.hasPrevious()){
			out.print(it.previous().id()+ " ");
		}
		out.println();
		out.println(pets);
		// You can also pass a integer in listIterator method. which refer the starting point of Iterator in the List.
		it = pets.listIterator(3);
		while(it.hasNext()){
			it.next();
			// Set method can be used to replace a value at index which is pointed by Iterator.
			it.set(Pets.randomPet());
		}
		out.println(pets);
	}
}

// Stack implementation using LinkedList 
class Stack<T> {
	LinkedList<T> storage = new LinkedList<T>();
	public void push(T a){storage.addFirst(a);}
	public T peek(){return storage.getFirst();}
	public T pop(){ return storage.removeFirst();}
	public boolean empty(){return storage.isEmpty();}
	public String toString(){return storage.toString();}
}

class StackTest{
	public static void main(String [] args){
		Stack<String> st = new Stack<String>();
		for(String s : "my dog is chutiya".split(" "))
			st.push(s);
		while(!st.empty()){
			out.print(st.pop() + " ");
		}
		out.println();
		// Either use full package name to avoid collission with previously define class 
		// or import class with full package name : ex : import mypackage.Stack;
		java.util.Stack<String> st1 = new java.util.Stack<String>();
		for(String s : "my dog is LOLWA".split(" "))
			st.push(s);
		while(!st.empty()){
			out.print(st.pop() + " ");
		}
	}
}










  
