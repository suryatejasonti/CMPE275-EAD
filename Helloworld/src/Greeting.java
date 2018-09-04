public class Greeting implements Greeter{
   private String name;
   
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name  = name;
	}
	@Override
	public String getGreeting() {
		// TODO Auto-generated method stub
		System.out.println("Hello world from " + name + "!");
		return null;
	}
}