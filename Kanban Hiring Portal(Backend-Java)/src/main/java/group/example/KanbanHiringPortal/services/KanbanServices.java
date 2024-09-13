package group.example.KanbanHiringPortal.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import group.example.KanbanHiringPortal.Repository.CandidateLoggerRepository;
import group.example.KanbanHiringPortal.Repository.CandidateRepository;
import group.example.KanbanHiringPortal.Repository.CommentRepository;
import group.example.KanbanHiringPortal.Repository.ContainerRepository;
import group.example.KanbanHiringPortal.Repository.ImageRepository;
import group.example.KanbanHiringPortal.Repository.TagsRepository;
import group.example.KanbanHiringPortal.Repository.UserRepository;
import group.example.KanbanHiringPortal.model.Candidate;
import group.example.KanbanHiringPortal.model.CandidateLogger;
import group.example.KanbanHiringPortal.model.Container;
import group.example.KanbanHiringPortal.model.Image;
import group.example.KanbanHiringPortal.model.ImageUploadResponse;
import group.example.KanbanHiringPortal.model.Tags;
import group.example.KanbanHiringPortal.model.User;
import group.example.KanbanHiringPortal.model.average;
import group.example.KanbanHiringPortal.model.comment;
import group.example.KanbanHiringPortal.model.search;
import group.example.KanbanHiringPortal.util.ImageUtility;
import lombok.Builder;
import java.time.temporal.ChronoUnit;

@Service
public class KanbanServices {

	@Autowired
	UserRepository Urepo;

	@Autowired
	CandidateRepository CRepo;

	@Autowired
	ImageRepository ImgRepo;

	@Autowired
	CommentRepository CoRepo;

	@Autowired
	TagsRepository TRepo;

	@Autowired
	CandidateLoggerRepository CLRepository;

	@Autowired
	ContainerRepository CtRepo;

	public User saveUser(User user) {
		return Urepo.save(user);
	}

	public User fetchUserByDasID(String dasId) {
		return Urepo.findByDasId(dasId);
	}

	public User fetchUserByDasIDAndPassword(String dasId, String password) {
		return Urepo.findByDasIdAndPassword(dasId, password);
	}

	public List<Candidate> findall() {
		return CRepo.findAll();
	}

	public void update(String status, String id) {
		System.out.println("The service hit");
		CRepo.update(status, id);
	}

	public Candidate getCandidateData(Candidate object) {
		String value = object.getCandidateId();
		return CRepo.returnObj(value);
	}

	public Candidate fetchUserByCandidateID(String Id) {
		// TODO Auto-generated method stub
		return CRepo.findByCandidateId(Id);
	}

	public Candidate saveCandidate(Candidate canObj) {
		// TODO Auto-generated method stub
		return CRepo.save(canObj);
	}

	public void updateGcm(String objValue, String objId) {
		// TODO Auto-generated method stub
		CRepo.updategcm(objValue, objId);

	}

	public void updatePosition(String objValue, String objId) {
		// TODO Auto-generated method stub
		System.out.println("Update Position Hit");
		System.out.println(objValue);
		System.out.println(objId);
		CRepo.updateposition(objValue, objId);
	}

	public void updateDuedate(String objValue, String objId) {
		// TODO Auto-generated method stub
		CRepo.updateduedate(objValue, objId);

	}

	public void updateProject(String objValue, String objId) {
		// TODO Auto-generated method stub
		CRepo.updateproject(objValue, objId);

	}

	public List<Candidate> searchingData(search searchObj) {
		// TODO Auto-generated method stub
		if (searchObj.getFilterValue().equals("gcm")) {
			return CRepo.searchGcm(searchObj.getInputData());
		} else if (searchObj.getFilterValue().equals("position")) {
			return (List<Candidate>) CRepo.searchPosition(searchObj.getInputData());
		} else if (searchObj.getFilterValue().equals("project")) {
			return CRepo.searchProject(searchObj.getInputData());
		} else if (searchObj.getFilterValue().equals("duedate")) {
			return CRepo.searchDueDate(searchObj.getInputData());
		} else if (searchObj.getFilterValue().equals("candidatename")) {
			return CRepo.searchCandidateName(searchObj.getInputData());
		} else if (searchObj.getFilterValue().equals("candidateid")) {
			return CRepo.searchCandidateId(searchObj.getInputData());
		}

		return null;

	}

	public List<comment> getAllComments(comment obj) {
		// TODO Auto-generated method stub

		return CoRepo.findAllComment(obj.getId());

	}

	public comment saveComment(comment comObj) {
		// TODO Auto-generated method stub
		return CoRepo.save(comObj);
	}

	public List<comment> getListOfComments(String id) {
		// TODO Auto-generated method stub
		return CoRepo.findAllComment(id);
	}

	public List<Candidate> writeSearchToCsv(search searchObj) {

		// TODO Auto-generated method stub
		List<Candidate> cand = null;
		if (searchObj.getFilterValue().equals("gcm")) {
			return CRepo.searchGcm(searchObj.getInputData());
		} else if (searchObj.getFilterValue().equals("position")) {
			return CRepo.searchPosition(searchObj.getInputData());
		} else if (searchObj.getFilterValue().equals("project")) {
			return CRepo.searchProject(searchObj.getInputData());
		} else if (searchObj.getFilterValue().equals("duedate")) {
			return CRepo.searchDueDate(searchObj.getInputData());
		} else if (searchObj.getFilterValue().equals("candidatename")) {
			return CRepo.searchCandidateName(searchObj.getInputData());
		} else if (searchObj.getFilterValue().equals("candidateid")) {
			return CRepo.searchCandidateId(searchObj.getInputData());
		}

		return cand;

	}

	public Image saveCandidateImage(Image image) {
		// TODO Auto-generated method stub
		System.out.println("Save candidate service hit");
		return ImgRepo.save(image);
	}

	public List<Candidate> generateMonthlyReport(String inputValue) {
		int inputValue1 = Integer.parseInt(inputValue);
		// TODO Auto-generated method stub
		return CRepo.findMonthlyData(inputValue1);
	}

	public List<Candidate> generateQuaterlyReport(int inputValue) {
		// TODO Auto-generated method stub
		return CRepo.findQuaterlyData(inputValue);
	}

	public average generateAverageReport(String inputValue) throws ParseException {
		// TODO Auto-generated method stub
		int average = 0;
		int sumOfDays = 0;
		List<Candidate> candList = CRepo.findLocationData(inputValue);
		for (int i = 0; i < candList.size(); i++) {
			Date duedate = new SimpleDateFormat("dd/MM/yyyy").parse(candList.get(i).getDuedate());
			java.util.Date utilDate = new java.util.Date(candList.get(i).getDate().getTime());
			Instant instant1 = duedate.toInstant();
			Instant instant2 = utilDate.toInstant();
			long daysBetween = ChronoUnit.DAYS.between(instant2, instant1);
			sumOfDays += daysBetween;
		}
		int Avgdays = sumOfDays / candList.size();
		average avgObj = new average(inputValue, Avgdays);

		return avgObj;
	}

	public List<Object> fetchAllLocation() {
		// TODO Auto-generated method stub

		return CRepo.fetchAllLocation();
	}

	public void delete_candidate(String id) {
		Candidate findById = CRepo.findById(id).get();
		CRepo.delete(findById);
	}

	public List<Tags> getListOfTags(String id) {
		// TODO Auto-generated method stub
		System.out.println("----------->tags");
		return TRepo.findAllTags(id);
	}

	public Tags saveTags(Tags tagsObj) {
		return TRepo.save(tagsObj);
	}

	public void updateTimestamp(String id, String userId, String prevContainer) {
		int logId = 0;

		Candidate candi = CRepo.findByCandidateId(id);
		System.out.println(candi.getCandidateId());
		Timestamp prevTime = candi.getTimestamp();
		System.out.println("prevTime " + prevTime);
		System.out.println("prevCont " + prevContainer);
		System.out.println("<________________>");
		Timestamp timestampFromSystemTime = new Timestamp(System.currentTimeMillis());
		CRepo.timestamp(timestampFromSystemTime, id);
		System.out.println("cureentTime " + timestampFromSystemTime);
		System.out.println("currentStatus " + candi.getStatus());

		Instant startInstant = prevTime.toInstant();
		Instant endInstant = timestampFromSystemTime.toInstant();

		Duration duration = Duration.between(startInstant, endInstant);
		long duration_in_milliseconds = duration.toMillis();
		System.out.println("diff miliseconds " + duration_in_milliseconds);
		String gotTime = containerTime(duration_in_milliseconds);
		System.out.println("Real Time " + gotTime);

		CandidateLogger candiLog = new CandidateLogger(logId, id, userId, prevContainer, gotTime, candi.getStatus(),
				timestampFromSystemTime);
		if (!prevContainer.equals(candi.getStatus())) {
			CLRepository.save(candiLog);
		}

	}

	public String containerTime(long duration_in_milliseconds) {
		long timeget = duration_in_milliseconds;
		String string = "";
		long remaindmiliseconds = 0;
		long days = Math.floorDiv(timeget, (24L * 3600L * 1000L));
		// long days = (long)(timeget / (24L * 3600L * 1000L));
		System.out.println(days);
		string = days + " Days ";
		remaindmiliseconds = timeget - (days * 24L * 3600L * 1000L);
		System.out.println(remaindmiliseconds);
		long hours = Math.floorDiv(remaindmiliseconds, (60L * 60L * 1000L));
		string = string + hours + " hours ";
		remaindmiliseconds = remaindmiliseconds - (hours * 60L * 60L * 1000L);
		System.out.println(remaindmiliseconds);
		long min = Math.floorDiv(remaindmiliseconds, (60L * 1000L));
		string = string + min + " minutes";
		System.out.println(string);
		return string;
	}

	public Container saveContainerObj(Container obj) {
		return CtRepo.save(obj);
	}

	public List<Container> getAllContainerData() {
		// TODO Auto-generated method stub
		return CtRepo.findAll();
	}

}
