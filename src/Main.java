public class Main {

    // todo: For now the main method is just going to be here for running quick unit tests on parts of Bar and Backend, eventually
    // it will probably take in args from somewhere to run with actual data.
    public static void main(String[] args){
        Backend backend = new Backend("so163hl", 10);
        backend.verifyPostcode("sp84hh");
        backend.verifyPostcode("SP84HH");
        backend.verifyPostcode("SO163HL");


    }
}
