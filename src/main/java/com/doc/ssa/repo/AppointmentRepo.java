package com.doc.ssa.repo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doc.ssa.model.Appointment;

public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {

	Optional<Appointment> findByAppointmentId(Integer appointmentId);

	@Query(nativeQuery = true, value = " SELECT "
			+ " a.appointment_id as appointmentId, doctor_name as doctorName, patient_name as patientName"
			+ " , age, mobile_no as mobileNo, address, date_format(appointment_date, '%d-%M-%Y') as appointmentDate, appointment_time as appointmentTime, appointment_for_desc as appointmentForDescription "
			//+ " , consultation_id as consultationId, prescription, rating, feedback "
			+ " , case when consultation_id is null then 'Pending for Consultation' else 'Closed' end as status "
			+ " from appointments a  "
			+ " left JOIN consultations b on a.appointment_id=b.appointment_id "
			+ " order by a.appointment_id ")
	List<Map<String, String>> findAllAppointments();
}
