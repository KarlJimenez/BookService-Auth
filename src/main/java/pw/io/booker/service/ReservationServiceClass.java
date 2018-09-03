package pw.io.booker.service;

import java.util.List;

import pw.io.booker.model.Image;
import pw.io.booker.model.Reservation;
import pw.io.booker.model.Service;
import pw.io.booker.repo.CustomerRepository;
import pw.io.booker.repo.ImageRepository;
import pw.io.booker.repo.ReservationRepository;
import pw.io.booker.repo.ServiceRepository;

public class ReservationServiceClass {

	private ReservationRepository reservationRepository;
	private ServiceRepository serviceRepository;
	private ImageRepository imageRepository;
	private CustomerRepository customerRepository;
	
	public ReservationServiceClass(ReservationRepository reservationRepository, ServiceRepository serviceRepository,
			ImageRepository imageRepository, CustomerRepository customerRepository) {
		super();
		this.reservationRepository = reservationRepository;
		this.serviceRepository = serviceRepository;
		this.imageRepository = imageRepository;
		this.customerRepository = customerRepository;
	}

	public List<Reservation> getAll() {
		return (List<Reservation>) reservationRepository.findAll();
	}
	
	public List<Reservation> saveAll(List<Reservation> reservations) {
		for (Reservation reservation : reservations) {
			if (reservationRepository.findById(reservation.getReservationId()).isPresent()) {
				throw new RuntimeException("Reservations already exist");
			}
			for (Service service : reservation.getAvailedServiceList()) {
				if (!serviceRepository.findById(service.getServiceId()).isPresent()) {
					throw new RuntimeException("Service doesn't exist");
				}

				for (Image image : service.getImages()) {
					if (!imageRepository.findById(image.getImageId()).isPresent()) {
						throw new RuntimeException("Image doesn't exist");
					}
				}

				if (!customerRepository.findById(reservation.getCustomer().getCustomerId()).isPresent()) {
					throw new RuntimeException("Customer doesn't exist");
				}
			}
		}
		return (List<Reservation>) reservationRepository.saveAll(reservations);
	}

	public List<Reservation> updateAll(List<Reservation> reservations) {
		for (Reservation reservation : reservations) {
			if (!reservationRepository.findById(reservation.getReservationId()).isPresent()) {
				throw new RuntimeException("Reservations should exist first");
			}
			for (Service service : reservation.getAvailedServiceList()) {
				if (!serviceRepository.findById(service.getServiceId()).isPresent()) {
					throw new RuntimeException("Service doesn't exist");
				}

				for (Image image : service.getImages()) {
					if (!imageRepository.findById(image.getImageId()).isPresent()) {
						throw new RuntimeException("Image doesn't exist");
					}
				}

				if (!customerRepository.findById(reservation.getCustomer().getCustomerId()).isPresent()) {
					throw new RuntimeException("Customer doesn't exist");
				}

				reservation.getAvailedServiceList().add(service);
			}
			reservationRepository.save(reservation);
		}
		return reservations;
	}

	public List<Reservation> deleteAll(List<Integer> reservationIdList) {
		List<Reservation> reservationList = (List<Reservation>) reservationRepository.findAllById(reservationIdList);
		reservationRepository.deleteAll(reservationList);
		return reservationList;
	}

	public Reservation getReservation(int reservationId) {
		return reservationRepository.findById(reservationId).get();
	}

	public Reservation updateReservation(int reservationId, Reservation reservation) {
		if (!reservationRepository.findById(reservation.getReservationId()).isPresent()) {
			throw new RuntimeException("Reservation should exist first");
		}
		for (Service service : reservation.getAvailedServiceList()) {
			if (!serviceRepository.findById(service.getServiceId()).isPresent()) {
				throw new RuntimeException("Service doesn't exist");
			}

			for (Image image : service.getImages()) {
				if (!imageRepository.findById(image.getImageId()).isPresent()) {
					throw new RuntimeException("Image doesn't exist");
				}
			}

			if (!customerRepository.findById(reservation.getCustomer().getCustomerId()).isPresent()) {
				throw new RuntimeException("Customer doesn't exist");
			}

		}
		reservationRepository.save(reservation);
		return reservation;
	}

	public Reservation deleteReservation(int reservationId) {
		Reservation reservation = reservationRepository.findById(reservationId).get();
		reservationRepository.delete(reservation);
		return reservation;
	}
}
