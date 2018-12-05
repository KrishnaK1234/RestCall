//package samplehazlecache;
//
//import java.io.Serializable;
//import java.net.InetAddress;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//import com.hazelcast.client.HazelcastClient;
//import com.hazelcast.client.config.ClientConfig;
//import com.hazelcast.client.config.ClientNetworkConfig;
//import com.hazelcast.core.HazelcastInstance;
//import com.hazelcast.core.IMap;
//
//
///**
// * <h1>HazelcastCacheContainer</h1> Class to maintain cache operations
// * 
// * @author GBOS Integration Technologies
// * 
// * @version 0.1
// * @since 19 September,2016
// */
//public class HazelcastCacheContainer implements ICacheContainer {
//
//	private static Exception exception = null;
//
//	/**
//	 * @return the exception
//	 */
//	public static Exception getException() {
//		return exception;
//	}
//
//	/**
//	 * @param exception the exception to set
//	 */
//	public static void setException(Exception exception) {
//		HazelcastCacheContainer.exception = exception;
//	}
//
//	/**
//	 * Description - method to retrieve values from cache
//	 * 
//	 * @param connection - Cache Connection
//	 * @param key        - String to retrieve value
//	 * @return returnDetails - referring return details
//	 * @throws Exception - method throws Exception
//	 */
//	@Override
//	public ReturnDetails getValues(Object connection, String key) throws Exception {
//// ReturnDetails object
//		ReturnDetails returnDetails = new ReturnDetails();
//		try {
//// get the object to hazelcast instance
//			HazelcastInstance hazelcastInstance = (HazelcastInstance) connection;
//// map to connect to cache
//			IMap<String, Serializable> cacheMap = hazelcastInstance
//					.getMap(System.getProperty(CacheConstants.HAZELCAST_MAP));
//// get the key with serializable object
//			Serializable object = cacheMap.get(key);
//// set the return details object
//			returnDetails.setObject(object);
//// set the status code
//			returnDetails.setStatusCode(GlobalConstants.SUCCESS);
//			return returnDetails;
//		} catch (Exception getException) {
//			setException(getException);
//// set the status code
//			returnDetails.setStatusCode(GlobalConstants.FAILURE);
//// throw exception
//			throw new Exception(getException, CacheExceptionMessages.GET_CACHE_FAILURE);
//		}
//
//	}
//
//	/**
//	 * Description - method to insert values in cache
//	 * 
//	 * @param connection - Cache Connection
//	 * @param key        - String to insert value
//	 * @param object     - Value to be inserted in cache
//	 * @return returnDetails - referring return details
//	 * @throws Exception - method throws Exception
//	 */
//	@Override
//	public ReturnDetails putValues(Object connection, String key, Serializable object) throws Exception {
//// ReturnDetails object
//		ReturnDetails returnDetails = new ReturnDetails();
//		try {
//// get the object to hazelcast instance
//			HazelcastInstance hazelcastInstance = (HazelcastInstance) connection;
//// map to connect to cache
//			IMap<String, Serializable> cacheMap = hazelcastInstance
//					.getMap(System.getProperty(CacheConstants.HAZELCAST_MAP));
//// insert the values into cache map
//			cacheMap.put(key, object);
//// set the status code
//			returnDetails.setStatusCode(GlobalConstants.SUCCESS);
//			return returnDetails;
//		} catch (Exception putException) {
//			setException(putException);
//// set the status code
//			returnDetails.setStatusCode(GlobalConstants.FAILURE);
//// throw exception
//			throw new Exception(putException, CacheExceptionMessages.PUT_CACHE_FAILURE);
//		}
//
//	}
//
//	/**
//	 * Description - method to insert all values in cache
//	 * 
//	 * @param connection - Cache Connection
//	 * @param putMap     - Map to be inserted in cache
//	 * @return returnDetails - referring return details
//	 * @throws Exception - method throws Exception
//	 */
//	@Override
//	public ReturnDetails putAll(Object connection, Map<String, Serializable> putMap) throws Exception {
//// ReturnDetails object
//		ReturnDetails returnDetails = new ReturnDetails();
//		try {
//// get the object to hazelcast instance
//			HazelcastInstance hazelcastInstance = (HazelcastInstance) connection;
//// map to connect to cache
//			IMap<String, Serializable> cacheMap = hazelcastInstance
//					.getMap(System.getProperty(CacheConstants.HAZELCAST_MAP));
//// insert the values into cache map
//			cacheMap.putAll(putMap);
//// set the status code
//			returnDetails.setStatusCode(GlobalConstants.SUCCESS);
//			return returnDetails;
//		} catch (Exception putAllException) {
//			setException(putAllException);
//// set the status code
//			returnDetails.setStatusCode(GlobalConstants.FAILURE);
//// throw exception
//			throw new Exception(putAllException, CacheExceptionMessages.PUT_ALL_CACHE_FAILURE);
//		}
//
//	}
//
//	/**
//	 * Description - method to get cache connection
//	 * 
//	 * @return returnDetails - referring return details
//	 * @throws Exception - method throws Exception
//	 */
//	@Override
//	public ReturnDetails getConnection() throws Exception {
//// ReturnDetails object
//		ReturnDetails returnDetails = new ReturnDetails();
//		try {
//			ClientConfig clientConfig = new ClientConfig();
//			clientConfig.getGroupConfig().setName(System.getProperty(CacheConstants.HAZELCAST_SERVER_USER))
//					.setPassword(System.getProperty(CacheConstants.HAZELCAST_SERVER_PWORD));
//			ClientNetworkConfig networkConfig = clientConfig.getNetworkConfig();
//			networkConfig.setConnectionAttemptLimit(CacheConstants.CONNECTION_ATTEMPT_LIMIT);
//			networkConfig.setConnectionAttemptPeriod(CacheConstants.CONNECTION_ATTEMPT_PERIOD);
//			networkConfig.setConnectionTimeout(CacheConstants.CONNECTION_TIMEOUT);
//			networkConfig.addAddress(InetAddress.getLocalHost().getHostAddress() + GlobalConstants.DELIMITER_COLON
//					+ System.getProperty(CacheConstants.HAZELCAST_DEFAULT_PORT));
//			HazelcastInstance hazelcastInstance = HazelcastClient.newHazelcastClient(clientConfig);
//
//			returnDetails.setStatusCode(GlobalConstants.SUCCESS);
//			returnDetails.setObject(hazelcastInstance);
//			return returnDetails;
//		} catch (Exception getConnectionException) {
//			setException(getConnectionException);
//// set the status code
//			returnDetails.setStatusCode(GlobalConstants.FAILURE);
//// throw exception
//			throw new Exception(getConnectionException,
//					CacheExceptionMessages.HAZELCAST_GET_CONNECTION_FAILURE);
//
//		}
//	}
//
//	/**
//	 * Description - method to delete values from cache
//	 * 
//	 * @param connection - Cache Connection
//	 * @param key        - String to delete value in cache
//	 * @throws Exception - method throws Exception
//	 */
//	@Override
//	public void delete(Object connection, String key) throws Exception {
//
//// ReturnDetails object
//		ReturnDetails returnDetails = new ReturnDetails();
//		try {
//// get the object to hazelcast instance
//			HazelcastInstance hazelcastInstance = (HazelcastInstance) connection;
//// map to connect to cache
//			IMap<String, Serializable> cacheMap = hazelcastInstance
//					.getMap(System.getProperty(CacheConstants.HAZELCAST_MAP));
//// delete value from cache map for specific key
//			cacheMap.remove(key);
//// set the status code
//			returnDetails.setStatusCode(GlobalConstants.SUCCESS);
//		} catch (Exception deleteException) {
//			setException(deleteException);
//// set the status code
//			returnDetails.setStatusCode(GlobalConstants.FAILURE);
//// throw exception
//			throw new Exception(deleteException, CacheExceptionMessages.HAZELCAST_DELETE_CACHE_FAILURE);
//		}
//
//	}
//
//	/**
//	 * Description - method to delete all values from cache
//	 * 
//	 * @param connection - Cache Connection
//	 * @throws Exception - method throws Exception
//	 */
//	@Override
//	public void deleteAll(Object connection) throws Exception {
//
//// ReturnDetails object
//		ReturnDetails returnDetails = new ReturnDetails();
//		try {
//// get the object to hazelcast instance
//			HazelcastInstance hazelcastInstance = (HazelcastInstance) connection;
//// map to connect to cache
//			IMap<String, Serializable> cacheMap = hazelcastInstance
//					.getMap(System.getProperty(CacheConstants.HAZELCAST_MAP));
//// clear cache map
//			cacheMap.clear();
//// set the status code
//			returnDetails.setStatusCode(GlobalConstants.SUCCESS);
//		} catch (Exception deleteAllException) {
//			setException(deleteAllException);
//// set the status code
//			returnDetails.setStatusCode(GlobalConstants.FAILURE);
//// throw exception
//			throw new Exception(deleteAllException,
//					CacheExceptionMessages.HAZELCAST_DELETE_ALL_CACHE_FAILURE);
//		}
//
//	}
//
//	@Override
//	public ReturnDetails putValueSpecificTimeInterval(Object connection, String key, Serializable object,
//			long timeToLive, TimeUnit timeUnit) throws Exception {
//		ReturnDetails returnDetails = new ReturnDetails();
//		try {
//// get the object to hazelcast instance
//			HazelcastInstance hazelcastInstance = (HazelcastInstance) connection;
//// map to connect to cache
//			IMap<Object, Object> cacheMap = hazelcastInstance.getMap(System.getProperty(CacheConstants.HAZELCAST_MAP));
//// insert the values into cache map
//			cacheMap.put(key, object, timeToLive, timeUnit);
//// set the status code
//			returnDetails.setStatusCode(GlobalConstants.SUCCESS);
//			return returnDetails;
//		} catch (Exception putException) {
//			setException(putException);
//// set the status code
//			returnDetails.setStatusCode(GlobalConstants.FAILURE);
//// throw exception
//			throw new Exception(putException, CacheExceptionMessages.PUT_CACHE_FAILURE);
//		}
//
//	}
//
//	@Override
//	public ReturnDetails doEvitOperation(Object connection, String key) throws Exception {
//		ReturnDetails returnDetails = new ReturnDetails();
//		try {
//// get the object to hazelcast instance
//			HazelcastInstance hazelcastInstance = (HazelcastInstance) connection;
//// map to connect to cache
//			IMap<Object, Object> cacheMap = hazelcastInstance.getMap(System.getProperty(CacheConstants.HAZELCAST_MAP));
//			cacheMap.evict(key);
//// set the status code
//			returnDetails.setStatusCode(GlobalConstants.SUCCESS);
//			return returnDetails;
//		} catch (Exception putException) {
//			setException(putException);
//// set the status code
//			returnDetails.setStatusCode(GlobalConstants.FAILURE);
//// throw exception
//			throw new Exception(putException, CacheExceptionMessages.PUT_CACHE_FAILURE);
//		}
//	}
//
//}