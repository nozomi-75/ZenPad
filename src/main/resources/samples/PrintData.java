public class PrintData {
	    public static void main(String[] args) {
        // INITIALIZE AND DECLARE VARIABLES
        String studentName = "Juan Dela Cruz"; // String is not a primitive data.
        String divider = "------------------------------------------";
        char studentGrade = 'B';
        byte studentAge = 36;
        short applicantNum = 27898;
        int totalNum = 97596000;
        long idNum = 136883130;
        float exp = 10.5378765f;
        double fees = 10500.047748255356463;
        
        // SET A BOOLEAN
        boolean stakes = exp>500;
        
        // PRINT LITERALS AND VARIABLES
        System.out.println("Welcome to the campus " + studentName);
        System.out.println("You are applicant no. " + applicantNum);
        System.out.println("There are a total of " + totalNum + " applicants");
        
        System.out.println(divider);
        System.out.println("PERSONAL DATA");
        System.out.println(divider);
        
        System.out.println("Applicant age: " + studentAge);
        System.out.println("Applicant ID No: " + idNum);
        System.out.println("Latest performance record: " + studentGrade);
        System.out.println("Campus credit: " + exp);
        System.out.println("Are you a campus staff: " + stakes);
        System.out.println("Remaining balance: " + fees);
    }
}
