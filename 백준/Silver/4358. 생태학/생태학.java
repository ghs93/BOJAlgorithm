import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		HashMap<String, Integer> map = new HashMap<>();
		int total = 0;
		
		while(br.ready()) {
			String str = br.readLine();
			total++;
			
			if(map.containsKey(str)) {
				map.put(str, map.get(str) + 1);
				
			} else {
				map.put(str, 1);
			}
		}
		
		int size = map.size();
		String[] keySet = map.keySet().toArray(new String[size]); 
		Arrays.sort(keySet);
		
		for (String key : keySet) {
			double avg = ((double)map.get(key) / total) * 100;
			System.out.printf("%s %.4f\n", key, avg);
		}
	}
}