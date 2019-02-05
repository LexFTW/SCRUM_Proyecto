package main.implementations;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MyObjectOutputStream extends ObjectOutputStream{

	public MyObjectOutputStream(OutputStream out) throws IOException{
		super(out);
	}

	public MyObjectOutputStream() throws IOException, SecurityException{
		super();
	}
	
	public void writeStreamHeader() throws IOException{
		
	}
	
}
