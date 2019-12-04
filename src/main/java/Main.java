public class Main {

    // todo: For now the main method is just going to be here for running quick unit tests on parts of Bar and Backend, eventually
    // it will probably take in args from somewhere to run with actual data.
    public static void main(String[] args){
        if (args[0].equals("test")) {
            System.out.println("Running tests");


            //postcode verification test
            Backend backend = new Backend("so163hl", 10);
            System.out.println(backend.verifyPostcode("sp84hh")); //true
            System.out.println(backend.verifyPostcode("SP8 4HH")); //true
            System.out.println(backend.verifyPostcode("SO16 3HL")); //true
            System.out.println(backend.verifyPostcode("SO 16 3H L")); //false

            //postcode information grabbing test
            System.out.println(backend.latLongFromPostcode("so16 3hl").toString());

        }

    }
}
