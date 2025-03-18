package cn.edu.hfut.java.exp2;

public class TransactionFactory {
    public static Transaction createTransaction(String str) {
        if(str==null||str.isEmpty())
            return null;
        String[] split = str.split("\t");
        switch (split[0].charAt(0)) {
            case 'A':
                return new AddTransaction(split[1],split[2],split[3]);
            case 'D':
                return new DeleteTransaction(split[1]);
            case 'O':
                return new OrderTransaction(split[1],Integer.parseInt(split[2]),split[3]);
            case 'R':
                return new ReceiveTransaction(split[1],Integer.parseInt(split[2]));
            default:
                return null;
        }
    }
}
