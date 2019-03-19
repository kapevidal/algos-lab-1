// You have a heist getaway sack with a capacity, and n items in front of you
// with sizes and worths. Figure out the maximum value you could
// get with the items.

// You are encouraged to make helper functions!

public class Robbery {

	// Using DP: Get the maximum value with capacity C and n items
	public int maximizeRobWorthRecur(
		int capacity,
		int[] sizes, //weight
		int[] worths,//values
		int n
	) 
	{
	    if (n == 0|| capacity == 0)
	    {
	        return 0;
	    }
	    if (sizes[n-1] > capacity)
	    {
	        return maximizeRobWorthRecur(capacity,sizes,worths,n-1);
	    } 
	    else
	    {
	        return Math.max(maximizeRobWorthRecur(capacity,sizes,worths,n-1),
	        		maximizeRobWorthRecur(capacity- sizes[n-1], sizes,worths,n-1)+ worths[n-1]);
	    }	
	}

	

	public int maximizeRobWorthBottomUp(
		int capacity,
		int[] sizes,
		int[] worths,
		int n
	) {
		  int i;
		  int w;
		  int K[][] = new int[n+1][capacity+1];   
		   for (i = 0; i <= n; i++) 
		   { 
		       for (w = 0; w <= capacity; w++) 
		       { 
		           if (i==0 || w==0) 
		               K[i][w] = 0; 
		           else if (sizes[i-1] <= w) 
		                 K[i][w] = Math.max(worths[i-1] + K[i-1][w-sizes[i-1]],  K[i-1][w]); 
		           else
		                 K[i][w] = K[i-1][w]; 
		       } 
		   } 	  
		   return K[n][capacity];
	}

	public int[] takeRobInventory(
			int capacity,
			int[] sizes,
			int[] worths,
			int n
			) 
	
	{
		 int i, w; 
	        int K[][] = new int[n + 1][capacity + 1]; 
	        for (i = 0; i <= n; i++) { 
	            for (w = 0; w <= capacity; w++) { 
	                if (i == 0 || w == 0) 
	                    K[i][w] = 0; 
	                else if (sizes[i - 1] <= w) 
	                    K[i][w] = Math.max(worths[i - 1] +  
	                              K[i - 1][w - sizes[i - 1]], K[i - 1][w]); 
	                else
	                    K[i][w] = K[i - 1][w]; 
	            } 
	        } 
	        int res = K[n][capacity]; 
	        int[] dptable =  new int[capacity];
	        
	        w = capacity; 
	        for (i = n; i > 0 && res > 0; i--) { 
	            if (res == K[i - 1][w]) 
	                continue; 
	            else { 
	                dptable[i]=(sizes[i - 1]); 
	                res = res - worths[i - 1]; 
	                w = w - sizes[i - 1]; 
	            } 
	        } 
	        return dptable;
	}

	public static void main(String[] args) {
		Robbery r = new Robbery();
		int bagCapacity = 40;
		int[] itemSizes = {2, 25, 6, 13, 1, 15, 8, 5, 17, 4};//weight
		int[] itemWorths = {35, 120, 900, 344, 29, 64, 67, 95, 33, 10};//value
		int n = itemWorths.length;
		
		int maxWorthRecur = r.maximizeRobWorthRecur(bagCapacity, itemSizes, itemWorths,n);
		System.out.println("Max worth of the bag: " + maxWorthRecur);
		int maxWorthBottomUp = r.maximizeRobWorthBottomUp(bagCapacity, itemSizes, itemWorths,n);
		System.out.println("Max worth of the bag: " + maxWorthBottomUp);


		 int[] itemsPicked = r.takeRobInventory(bagCapacity, itemSizes, itemWorths, n);
		System.out.println("The items we take are worth:");
		 for (int i = 0; i < itemsPicked.length; i++)
		 {
			 if(itemsPicked[i]!=0)
		 	System.out.print(itemsPicked[i] + " ");	
	}
	}
}