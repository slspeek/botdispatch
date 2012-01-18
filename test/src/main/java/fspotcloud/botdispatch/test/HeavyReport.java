package fspotcloud.botdispatch.test;


public class HeavyReport {
    
        static String report;
        
        public HeavyReport() {}
        public void report(String message) {
            report = message;
        }

        public void error(Throwable error) {
        }
}
