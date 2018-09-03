package pw.io.booker.controller;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pw.io.booker.model.Reservation;
import pw.io.booker.service.ReservationServiceClass;

@RestController
@Transactional
@RequestMapping("/reservations")
public class ReservationController {

	private ReservationServiceClass reservationServiceClass;

	public ReservationController(ReservationServiceClass reservationServiceClass) {
		super();
		this.reservationServiceClass = reservationServiceClass;
	}

	@GetMapping
	public List<Reservation> getAll() {
		return reservationServiceClass.getAll();
	}

	@PostMapping
	public List<Reservation> saveAll(@RequestBody List<Reservation> reservations) {
		return reservationServiceClass.saveAll(reservations);
	}

	@PutMapping
	public List<Reservation> updateAll(@RequestBody List<Reservation> reservations) {
		return reservationServiceClass.updateAll(reservations);
	}

	@DeleteMapping
	public List<Reservation> deleteAll(@RequestParam("reservationIdList") List<Integer> reservationIdList) {
		return reservationServiceClass.deleteAll(reservationIdList);
	}

	@GetMapping("/{reservationId}")
	public Reservation getReservation(@PathVariable("reservationId") int reservationId) {
		return reservationServiceClass.getReservation(reservationId);
	}

	@PutMapping("/{reservationId}")
	public Reservation updateReservation(@PathVariable("reservationId") int reservationId,
			@RequestBody Reservation reservation) {
		return reservationServiceClass.updateReservation(reservationId, reservation);
	}

	@DeleteMapping("/{reservationId}")
	public Reservation deleteReservation(@PathVariable("reservationId") int reservationId) {
		return reservationServiceClass.deleteReservation(reservationId);
	}
}
