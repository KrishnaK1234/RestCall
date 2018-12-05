//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//
//import org.mongojack.DBCursor;
//import org.mongojack.DBQuery;
//import org.mongojack.JacksonDBCollection;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.stereotype.Component;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
//import com.hazelcast.core.HazelcastInstance;
//import com.hazelcast.core.MapLoaderLifecycleSupport;
//import com.hazelcast.core.MapStore;
//
//import com.mongodb.MongoClient;
//
///**
// * <h1>DefaultNoSQLMapStore</h1>
// * <p>
// * This DefaultNoSQLMapStore class will be called by the IMDG internally when
// * any specific operations like get, loadAll, put, etc are performed on the
// * distributed map. It is also responsible for interacting with the persistent
// * data store and perform load/store/delete operations.
// * </p>
// * 
// * @version 0.1
// * @since 20 May,2017
// */
//@Component
//public class DefaultNoSQLMapStore
//		implements MapStore<String, Object>, ApplicationContextAware, MapLoaderLifecycleSupport {
//
//	private static final String MONGO_DB_UNIQUE_DOCUMENT_ID = "_id";
//	private static final String IMDG_LOAD_METHOD_CALLED = "IMDG LOAD METHOD CALLED ";
//	private static final String IMDG_LOADALL_METHOD_CALLED = "IMDG LOADALL METHOD CALLED";
//	private static final String IMDG_LOAD_ALL_KEYS_METHOD_CALLED = "IMDG LOAD ALL KEYS METHOD CALLED";
//	private static final String IMDG_STORE_METHOD_CALLED = "IMDG STORE METHOD CALLED ";
//	private static final String IMDG_STORE_ALL_METHOD_CALLED = "IMDG STORE ALL METHOD CALLED ";
//	private static final String IMDG_DELETE_METHOD_CALLED = "IMDG DELETE METHOD CALLED ";
//	private static final String IMDG_DELETE_ALL_METHOD_CALLED = "IMDG DELETE ALL METHOD CALLED ";
//	private static final String IMDG_LOADALL_KEYS_EXCEPTION_OCCURED = "Exception Occured in Load ALL KEYS METHOD : EXCEPTION IS :";
//	private static final String IMDG_LOAD_ALL_EXCEPTION_OCCURED = "Exception Occured in Load ALL METHOD : EXCEPTION IS :";
//	private static final String IMDG_DESTROY_METHOD_CALLED = "IMDG DESTROY METHOD CALLED ";
//
//	private static final Logger logger = LoggerFactory.getLogger(DefaultNoSQLMapStore.class);
//	private MongoClient mongoClient;
//	private static ApplicationContext context;
//	JacksonDBCollection<DocumentWrapper, String> collection;
//	private ObjectMapper mapper = new ObjectMapper();
//	private AES256Encryption encryption;
//
//	// private String encryptionFlag;
//
//	/**
//	 * This init method will be called by the IMDG map loader life cycle support
//	 * 
//	 * @param hazelcastInstance IMDG instance
//	 * 
//	 * @param properties        for loading from the XML file
//	 * 
//	 * @param mapName           name of the map
//	 */
//	@SuppressWarnings({ "deprecation" })
//	@Override
//	public void init(HazelcastInstance hazelcastInstance, Properties properties, String mapName) {
//		try {
//			this.mongoClient = (MongoClient) context.getBean("mongoclient");
//			mapper.registerModule(new ParameterNamesModule()).registerModule(new Jdk8Module())
//					.registerModule(new JavaTimeModule());
//			collection = JacksonDBCollection.wrap(
//					mongoClient.getDB(context.getEnvironment().getProperty("mongodb.db")).getCollection(mapName),
//					DocumentWrapper.class, String.class);
//
//			this.encryption = context.getBean(AES256Encryption.class);
//			// this.encryptionFlag =
//			// context.getEnvironment().getProperty("encryption.flag");
//		} catch (Exception exception) {
//			logger.error(exception.getMessage(), exception);
//
//		}
//	}
//
//	/**
//	 * This load method is called by IMDG when value is requested by the key using
//	 * get method. MapStore will check if the requested key is available in the IMDG
//	 * and if it not available it will go to persistent data store and load it into
//	 * the IMDG.
//	 * 
//	 * @param key for load specific key from cache
//	 * 
//	 * @return object from IMDG server
//	 * 
//	 */
//	@Override
//	public Object load(String key) {
//		logger.debug(IMDG_LOAD_METHOD_CALLED);
//		try {
//			DocumentWrapper wrapper = collection.findOneById(key);
//			if (wrapper != null) {
//				String decryptedString = wrapper.getObjectJsonString();
//				return mapper.readValue(decryptedString, wrapper.getClassType());
//			}
//		} catch (Exception exception) {
//			logger.error(IMDG_LOAD_ALL_EXCEPTION_OCCURED + exception.getMessage(), exception);
//			exception.printStackTrace();
//
//		}
//		return null;
//	}
//
//	/**
//	 * This Load All method with parameter collection of keys will be called by the
//	 * IMDG when the IMap.get ,put operation is called. It will asynchronously loads
//	 * all the entries from database and insert into the IMDG server.
//	 * 
//	 * @param keys passing collection of keys
//	 * 
//	 * @return map object
//	 */
//	@Override
//	public Map<String, Object> loadAll(Collection<String> keys) {
//		logger.debug(IMDG_LOADALL_METHOD_CALLED);
//
//		Map<String, Object> documentWrapperMap = new HashMap<String, Object>();
//		try {
//			for (String key : keys) {
//				DocumentWrapper wrapper = collection.findOneById(key);
//				documentWrapperMap.put(wrapper.getId(),
//						mapper.readValue(wrapper.getObjectJsonString(), wrapper.getClassType()));
//			}
//		} catch (Exception exception) {
//			logger.error(IMDG_LOAD_ALL_EXCEPTION_OCCURED + exception.getMessage(), exception);
//			exception.printStackTrace();
//
//		}
//		return documentWrapperMap;
//	}
//
//	/**
//	 * This Load All keys method will be called by the IMDG when the IMap.get ,put,
//	 * loadAll operation is called and load all keys from the database and pass it
//	 * to the load ALL method and load all method will insert it into cache.
//	 * 
//	 * @return keys list of iterable keys
//	 */
//	@Override
//	public Iterable<String> loadAllKeys() {
//		logger.debug(IMDG_LOAD_ALL_KEYS_METHOD_CALLED);
//		List<String> keys = new ArrayList<String>();
//		try {
//			DBCursor<DocumentWrapper> cursor;
//			DBQuery.Query query = DBQuery.exists(MONGO_DB_UNIQUE_DOCUMENT_ID);
//			cursor = collection.find(query);
//			Iterator<DocumentWrapper> iterator = cursor.iterator();
//			while (iterator.hasNext()) {
//				keys.add(iterator.next().getId());
//			}
//		} catch (Exception exception) {
//			logger.error(IMDG_LOADALL_KEYS_EXCEPTION_OCCURED + exception.getMessage(), exception);
//			exception.printStackTrace();
//
//		}
//		return keys;
//	}
//
//	/**
//	 * This store method will be called by the IMDG when the IMap.put operation is
//	 * performed and it will persist the key/value pairs in the database
//	 * 
//	 * @param key   to be inserted in the IMDG server
//	 * 
//	 * @param value to be inserted in the IMDG Server
//	 */
//	@Override
//	public void store(String key, Object value) {
//		try {
//			String ObjectJson = mapper.writeValueAsString(value);
//			// String encryptedString = this.encryption.encrypt(ObjectJson);
//			DocumentWrapper wrapper = new DocumentWrapper(key, ObjectJson, value.getClass());
//			this.collection.save(wrapper);
//		} catch (Exception exception) {
//			logger.error(exception.getMessage(), exception);
//
//		}
//		logger.debug(IMDG_STORE_METHOD_CALLED);
//	}
//
//	/**
//	 * This storeAll method will be called by the IMDG when the IMap.putAll
//	 * operation is performed and it will persist batch of key/value pairs in the
//	 * database
//	 * 
//	 * @param map object to be inserted into IMDG server.
//	 * 
//	 */
//	@Override
//	public void storeAll(Map<String, Object> map) {
//		try {
//			List<DocumentWrapper> batch = new ArrayList<DocumentWrapper>();
//			for (Map.Entry<String, Object> entry : map.entrySet()) {
//				String key = entry.getKey();
//				String encryptedString = mapper.writeValueAsString(entry.getValue());
//				Class<? extends Object> className = entry.getValue().getClass();
//				batch.add(new DocumentWrapper(key, encryptedString, className));
//			}
//			this.collection.insert(batch);
//			logger.debug(IMDG_STORE_ALL_METHOD_CALLED);
//		} catch (Exception exception) {
//			logger.error(exception.getMessage(), exception);
//
//		}
//	}
//
//	/**
//	 * This store method will be called by the IMDG when the IMap.delete operation
//	 * is performed and it will delete the key/value pairs in the database
//	 * 
//	 * @param key that needs to be deleted from IMDG server.
//	 */
//	@Override
//	public void delete(String key) {
//		logger.debug(IMDG_DELETE_METHOD_CALLED);
//		try {
//			collection.removeById(key);
//		} catch (Exception exception) {
//			logger.error(exception.getMessage(), exception);
//
//		}
//
//	}
//
//	/**
//	 * This deleteAll method will be called by the IMDG when the IMap.deleteAll
//	 * operation is performed and it will delete batch of key/value pairs in the
//	 * database
//	 * 
//	 * @param keys list of keys that needs to be deleted at a time
//	 */
//	@Override
//	public void deleteAll(Collection<String> keys) {
//		logger.debug(IMDG_DELETE_ALL_METHOD_CALLED);
//		try {
//			for (String key : keys) {
//				collection.removeById(key);
//			}
//		} catch (Exception exception) {
//			logger.error(exception.getMessage(), exception);
//
//		}
//	}
//
//	/**
//	 * This destroy method will be called by the IMDG map loader life cycle support
//	 */
//	@Override
//	public void destroy() {
//		logger.debug(IMDG_DESTROY_METHOD_CALLED);
//		// try {
//		// collection.drop();
//		// } catch (Exception exception) {
//		// logger.error(exception.getMessage(), exception);
//		// this.notification.exceptionNotification(exception);
//		// }
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * org.springframework.context.ApplicationContextAware#setApplicationContext
//	 * (org.springframework.context.ApplicationContext)
//	 */
//	@Override
//	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//		DefaultNoSQLMapStore.context = applicationContext;
//
//	}
//
//	/*	*//**
//			 * Method to return the encrypted string based on the encryption flag
//			 * 
//			 * @param json
//			 * @return
//			 */
//	/*
//	 * public String encryptionDecide(String json) { return (encryptionFlag ==
//	 * "true") ? this.encryption.encrypt(json) : json;
//	 * 
//	 * }
//	 *//**
//		 * Method to return the decrypted string based on the encryption flag
//		 * 
//		 * @param json
//		 * @return
//		 */
//	/*
//	 * public String encryptionDecide(String json) { return (encryptionFlag ==
//	 * "true") ? this.encryption.decrypt(json) : json;
//	 * 
//	 * }
//	 */
//}
