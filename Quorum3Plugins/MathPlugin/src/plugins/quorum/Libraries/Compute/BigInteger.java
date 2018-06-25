
package plugins.quorum.Libraries.Compute;
import quorum.Libraries.Compute.BigInteger_;

public class BigInteger {
    public java.lang.Object me_ = null;
    public java.math.BigInteger value;
    
    public java.math.BigInteger GetValue() {
        return value;
    }
    
    public BigInteger() {
        java.math.BigInteger bigInt = new java.math.BigInteger("0");
        value = bigInt;
    }
    
    private BigInteger(java.math.BigInteger b) {
        value = b;
    } 
    
    public void SetValue(String v, int base){
        java.math.BigInteger bigInt = new java.math.BigInteger(v, base);
        value = bigInt;
    }
    
    public String GetText(int base) {
        return value.toString(base);
    }
    
    public BigInteger_ Add(BigInteger_ b) {
       java.math.BigInteger castB = ((quorum.Libraries.Compute.BigInteger)b).plugin_.value; 
        
       java.math.BigInteger v = this.value.add(castB);
       
       quorum.Libraries.Compute.BigInteger name = new quorum.Libraries.Compute.BigInteger();
       name.plugin_.value= v;
       
       return name;
    }
    
    public BigInteger_ And(BigInteger_ b) {
        
       java.math.BigInteger castB = ((quorum.Libraries.Compute.BigInteger)b).plugin_.value; 
        
       java.math.BigInteger v = this.value.and(castB);
       
       quorum.Libraries.Compute.BigInteger name = new quorum.Libraries.Compute.BigInteger();
       name.plugin_.value= v;
       
       return name;
    }
    
    public BigInteger_ AndNot(BigInteger_ b) {
        
       java.math.BigInteger castB = ((quorum.Libraries.Compute.BigInteger)b).plugin_.value; 
        
       java.math.BigInteger v = this.value.andNot(castB);
       
       quorum.Libraries.Compute.BigInteger name = new quorum.Libraries.Compute.BigInteger();
       name.plugin_.value= v;
       
       return name;
    }
    
    public int CompareResult(BigInteger_ b) {
        
       java.math.BigInteger castB = ((quorum.Libraries.Compute.BigInteger)b).plugin_.value; 
        
       int v = this.value.compareTo(castB);
      
       return v;
    }
    
     public BigInteger_ Divide(BigInteger_ b) {
        
       java.math.BigInteger castB = ((quorum.Libraries.Compute.BigInteger)b).plugin_.value; 
        
       java.math.BigInteger v = this.value.divide(castB);
       
       quorum.Libraries.Compute.BigInteger name = new quorum.Libraries.Compute.BigInteger();
       name.plugin_.value= v;
       
       return name;
    }
     
    public boolean Equals(BigInteger_ b) {
        
       java.math.BigInteger castB = ((quorum.Libraries.Compute.BigInteger)b).plugin_.value; 
        
       boolean v = this.value.equals(castB);
       
       return v;
    }
    
    
    public double ToNumber() {
        
       double number = value.doubleValue();
       
       return number;
    }
    
    public int ToInteger() {
        
       int number = value.intValue();
       
       return number;
    }
    
    public BigInteger_ GreatestCommonDivisor(BigInteger_ b) {
        
       java.math.BigInteger castB = ((quorum.Libraries.Compute.BigInteger)b).plugin_.value; 
        
       java.math.BigInteger v = this.value.gcd(castB);
       
       quorum.Libraries.Compute.BigInteger name = new quorum.Libraries.Compute.BigInteger();
       name.plugin_.value= v;
       
       return name;
    }
            

    public BigInteger_ GetMaximumValue(BigInteger_ b) {
        
       java.math.BigInteger castB = ((quorum.Libraries.Compute.BigInteger)b).plugin_.value; 
        
       java.math.BigInteger v = this.value.max(castB);
       
       quorum.Libraries.Compute.BigInteger name = new quorum.Libraries.Compute.BigInteger();
       name.plugin_.value= v;
       
       return name;
    }
    
    
    public BigInteger_ GetMinimumValue(BigInteger_ b) {
        
       java.math.BigInteger castB = ((quorum.Libraries.Compute.BigInteger)b).plugin_.value; 
        
       java.math.BigInteger v = this.value.min(castB);
       
       quorum.Libraries.Compute.BigInteger name = new quorum.Libraries.Compute.BigInteger();
       name.plugin_.value= v;
       
       return name;
    }
    
    public BigInteger_ Mod(BigInteger_ b) {
        
       java.math.BigInteger castB = ((quorum.Libraries.Compute.BigInteger)b).plugin_.value; 
        
       java.math.BigInteger v = this.value.mod(castB);
       
       quorum.Libraries.Compute.BigInteger name = new quorum.Libraries.Compute.BigInteger();
       name.plugin_.value= v;
       
       return name;
    }
    
    public BigInteger_ Multiply(BigInteger_ b) {
        
       java.math.BigInteger castB = ((quorum.Libraries.Compute.BigInteger)b).plugin_.value; 
        
       java.math.BigInteger v = this.value.multiply(castB);
       
       quorum.Libraries.Compute.BigInteger name = new quorum.Libraries.Compute.BigInteger();
       name.plugin_.value= v;
       
       return name;
    }
    
    public BigInteger_ Negate() {
        
       java.math.BigInteger v = this.value.negate();
       
       quorum.Libraries.Compute.BigInteger name = new quorum.Libraries.Compute.BigInteger();
       name.plugin_.value= v;
       
       return name;
    }
    
    public BigInteger_ BitwiseNot() {
        
       java.math.BigInteger v = this.value.not();
       
       quorum.Libraries.Compute.BigInteger name = new quorum.Libraries.Compute.BigInteger();
       name.plugin_.value= v;
       
       return name;
    }
    
    public BigInteger_ Or(BigInteger_ b) {
        
       java.math.BigInteger castB = ((quorum.Libraries.Compute.BigInteger)b).plugin_.value; 
        
       java.math.BigInteger v = this.value.or(castB);
       
       quorum.Libraries.Compute.BigInteger name = new quorum.Libraries.Compute.BigInteger();
       name.plugin_.value= v;
       
       return name;
    }    

    
    public BigInteger_ Remainder(BigInteger_ b) {
        
       java.math.BigInteger castB = ((quorum.Libraries.Compute.BigInteger)b).plugin_.value; 
        
       java.math.BigInteger v = this.value.remainder(castB);
       
       quorum.Libraries.Compute.BigInteger name = new quorum.Libraries.Compute.BigInteger();
       name.plugin_.value= v;
       
       return name;
    }
    
    public BigInteger_ Subtract(BigInteger_ b) {
        
       java.math.BigInteger castB = ((quorum.Libraries.Compute.BigInteger)b).plugin_.value; 
        
       java.math.BigInteger v = this.value.subtract(castB);
       
       quorum.Libraries.Compute.BigInteger name = new quorum.Libraries.Compute.BigInteger();
       name.plugin_.value= v;
       
       return name;
    }
    
    public BigInteger_ ExclusiveOr(BigInteger_ b) {
        
       java.math.BigInteger castB = ((quorum.Libraries.Compute.BigInteger)b).plugin_.value; 
        
       java.math.BigInteger v = this.value.xor(castB);
       
       quorum.Libraries.Compute.BigInteger name = new quorum.Libraries.Compute.BigInteger();
       name.plugin_.value= v;
       
       return name;
    }
    
    public BigInteger_ RaiseToPower(int power) {
        
        
       java.math.BigInteger v = this.value.pow(power);
       
       quorum.Libraries.Compute.BigInteger name = new quorum.Libraries.Compute.BigInteger();
       name.plugin_.value= v;
       
       return name;
    }
    
    public BigInteger_ ShiftLeft(int positions) {
        
       java.math.BigInteger v = this.value.shiftLeft(positions);
       
       quorum.Libraries.Compute.BigInteger name = new quorum.Libraries.Compute.BigInteger();
       name.plugin_.value= v;
       
       return name;
    }
    
    
    public BigInteger_ ShiftRight(int positions) {
        
       java.math.BigInteger v = this.value.shiftRight(positions);
       
       quorum.Libraries.Compute.BigInteger name = new quorum.Libraries.Compute.BigInteger();
       name.plugin_.value= v;
       
       return name;
    }
    
    public int GetSignValue(){
        return this.value.signum();
    }
}
