import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("create a new account");
        System.out.print("enter your account name:");
        String account = sc.next();
        System.out.print("enter your password:");
        String password = sc.next();
        User user = new User(account,password);
        try{
            SerializeUtil.saveObject(user);
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("an exception occurred while saving");
        }

        User readObj;
        try{
            readObj = (User)SerializeUtil.readObject();
            System.out.println(readObj);
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("an exception occurred while reading");
        }
        sc.close();
    }
}
class User implements Serializable{
    private static final long serialVersionUID = 1;
    private String account;
    private String password;
    public User(String account,String password){
        this.account=account;
        this.password=password;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }
    public String toString(){
        return "User:"+account+" password:"+password;
    }
}
class SerializeUtil {
    public static void saveObject(Object obj) throws Exception{
        ObjectOutputStream out = null;
        FileOutputStream fout = null;
        try{
            fout = new FileOutputStream("user.dat");
            out = new ObjectOutputStream(fout);
            out.writeObject(obj);
        }finally {
            fout.close();
            out.close();
        }
    }
    public static Object readObject() throws Exception{
        ObjectInputStream in = null;
        FileInputStream fin = null;
        try{
            fin = new FileInputStream("user.dat");
            in = new ObjectInputStream(fin);
            return in.readObject();
        }finally {
            fin.close();
            in.close();
        }
    }
}
class Generator {
    private static final int MIN = 1;
    private static final int MAX = 100;
    private static final Random r = new Random();
    public Generator(){
        for(int i=0;i<10;i++){
            int a = r.nextInt(MAX)+1;
            int b = r.nextInt(MAX)+1;
            boolean isAdd = r.nextBoolean();
            String operator = isAdd ? "+":"-";
            System.out.println(i+"."+a+operator+b+"=");
        }
    }
}