//
//package samplehazlecache;
//
//import java.io.Serializable;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.util.HashMap;
//import java.util.Map;
//
//import com.hazelcast.client.HazelcastClient;
//import com.hazelcast.client.config.ClientConfig;
//import com.hazelcast.client.config.ClientNetworkConfig;
//import com.hazelcast.core.HazelcastInstance;
//import com.hazelcast.core.IMap;
//import com.hazelcast.nio.Connection;
//
//public class HazelCacheConnection {
//	public static void main(String args[]) throws UnknownHostException {
//		Connection connection = null;
//
//		ClientConfig clientConfig = new ClientConfig();
//		clientConfig.getGroupConfig().setName("dev").setPassword("dev-pass");
//		ClientNetworkConfig networkConfig = clientConfig.getNetworkConfig();
//		networkConfig.setConnectionAttemptLimit(5);
//		networkConfig.setConnectionAttemptPeriod(30000);
//		networkConfig.setConnectionTimeout(10000);
////networkConfig.set
//		networkConfig.addAddress(InetAddress.getLocalHost().getHostAddress() + ":" + "5701");
//		HazelcastInstance hazelcastInstance = HazelcastClient.newHazelcastClient(clientConfig);
//		System.out.print("hazlecastconnected");
//
//		SampleUser sampleobject = new SampleUser();
//		sampleobject.setFirstname("Srivalli");
//		sampleobject.setUserid("srman4");
////Map<String, SampleUser> putMap = new HashMap<String, SampleUser>();
//// putMap.put("USER-1",sampleobject);
//		IMap<String, Serializable> cacheMap = hazelcastInstance.getMap("samplemap");
//		cacheMap.put("sampleObj", sampleobject);
//		System.out.print("put completed ");
//
//// get the key with serializable object
//		SampleUser object = (SampleUser) cacheMap.get("sampleObj");
//		System.out.println(object.getFirstname() + " : " + object.getUserid());
//		String s = "";
//
//	}
//
//}
