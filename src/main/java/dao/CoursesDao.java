package dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Flow.Subscriber;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.reactivestreams.Publisher;

import com.google.gson.Gson;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.AggregatePublisher;
import com.mongodb.reactivestreams.client.FindPublisher;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;

import db.Connection;
import entity.Course;
import entity.Instructor;

public class CoursesDao {
	private MongoClient mongoClient;
	private MongoCollection<Course> collection;
	private MongoCollection<Document> docColl;
	private MongoDatabase db;

	public CoursesDao() {
		mongoClient = Connection.getInstance().getMongoClient();
		db = mongoClient.getDatabase("AnGK1");
		CodecRegistry registry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		collection = db.getCollection("courses", Course.class).withCodecRegistry(registry);
		docColl = db.getCollection("courses");
	}

//	db.courses.createIndex({name:'text',description:'text'})
//	db.courses.find({$text:{$search:'Biology'}})
	public List<Course> getCourses(String keywords) {
		List<Course> list = new ArrayList<Course>();
		Publisher<String> p = collection.createIndex(Document.parse("{name:'text',description:'text'}"));
		CouSubscriber<String> s = new CouSubscriber<String>();
		p.subscribe(s);
		if (s.getSingleResult() != null) {
			FindPublisher<Document> publisher = docColl.find(Document.parse("{$text:{$search:'" + keywords + "'}}"));
			CouSubscriber<Document> subscriber = new CouSubscriber<Document>();
			publisher.subscribe(subscriber);
			Gson gson = new Gson();

			for (Document doc : subscriber.getResults()) {
				Course course = gson.fromJson(doc.toJson(), Course.class);
				list.add(course);
			}
		}
		return list;
	}

	public Instructor getInstructor(Document doc) {
		return new Instructor(doc.getString("id"), doc.getString("name"), doc.getString("email"),
				doc.getString("phone"));
	}

//	db.courses.aggregate([{$unwind:'$sections'},{$group:{_id:'$sections.instructor',total:{$sum:1}}},{$sort:{'_id.name':1}}])
	public LinkedHashMap<Instructor, Integer> getNumberOfCoursesByInstructor() {
		AggregatePublisher<Document> publisher = docColl.aggregate(List.of(Document.parse("{$unwind:'$sections'}"),
				Document.parse("{$group:{_id:'$sections.instructor',total:{$sum:1}}}"),
				Document.parse("{$sort:{'_id.name':1}}")));
		CouSubscriber<Document> subscriber = new CouSubscriber<Document>();
		publisher.subscribe(subscriber);
		return subscriber.getResults().stream()
				.collect(Collectors.toMap(doc -> getInstructor(doc.get("_id", Document.class)),
						doc -> doc.getInteger("total"), (old, newValue) -> old, LinkedHashMap::new));
	}

//	 db.courses.updateOne( { _id: 'BIO101', 'sections.sectionNo': 'BIO101-01' }, { $set: { 'sections.$.room': 'C-201' } })
	public boolean updateTheRoomOfSection(String courseId, String sectionNo, String newRoom) {
		Publisher<UpdateResult> publisher = docColl.updateOne(
				Document.parse("{_id:'" + courseId + "', 'sections.sectionNo': '" + sectionNo + "'}"),
				Document.parse("{$set:{'sections.$.room': '" + newRoom + "'}}"));
		CouSubscriber<UpdateResult> subscriber = new CouSubscriber<>();
		publisher.subscribe(subscriber);
		return subscriber.getSingleResult().getModifiedCount() > 0;
	}
}
