
public class Task4_5 {
    public static void main(String[] args){
        System.out.println(BMI("205 pounds", "73 inches"));
        System.out.println(BMI("55 kilos", "1.65 meters"));
        System.out.println(BMI("154 pounds", "2 metres")); 
        }
        
    public static String BMI(String weight, String height) {
    
            String[] w = weight.split(" ");
            String[] h = height.split(" ");
            String ans = "";
    
            double kilos = w[1].equals("pounds") ? Double.parseDouble(w[0]) * 0.453592 : Double.parseDouble(w[0]);
            double meters = h[1].equals("inches") ? Double.parseDouble(h[0]) * 0.0254 : Double.parseDouble(h[0]);
    
            double val = kilos / (meters * meters);
            val = Math.round(val * 10.0) / 10.0;
    
            if (val < 18.5) ans += val+" Underweight";
            if (val >= 18.5 && val <= 24.5) ans += val + " Normal weight";
            if (val >= 25) ans += val + " Overweight";
            return ans;
    
        }
}