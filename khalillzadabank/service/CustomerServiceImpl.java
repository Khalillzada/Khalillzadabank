package com.example.khalillzadabank.service;
import com.example.khalillzadabank.dto.request.ReqCustomer;
import com.example.khalillzadabank.dto.response.RespCustomer;
import com.example.khalillzadabank.dto.response.RespStatus;
import com.example.khalillzadabank.dto.response.Response;
import com.example.khalillzadabank.entitiy.Customer;
import com.example.khalillzadabank.enums.EnumAvailableStatus;
import com.example.khalillzadabank.exception.BankException;
import com.example.khalillzadabank.exception.ExceptionConstants;
import com.example.khalillzadabank.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    @Override
    public Response<List<RespCustomer>> customerList() {
        Response<List<RespCustomer>> response = new Response<>();
        try {
            List<Customer> customerList = customerRepository.findAllByActive(EnumAvailableStatus.ACTIVE.value);
            if (customerList.isEmpty()) {
                throw new BankException(ExceptionConstants.CUSTOMER_NOT_FOUND,"Customer not found");
            }
            List<RespCustomer> respCustomerList = customerList.stream().map(this::mapping).collect(Collectors.toList());
            response.setT(respCustomerList);
            response.setStatus(RespStatus.getsuccessMessage());
        }catch (BankException ex){
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex){
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
            ex.printStackTrace();
        }

        return response;
    }

    @Override
    public Response<RespCustomer> customerById(Long id) {
        Response<RespCustomer> response = new Response<>();
        try {
           if (id == null){

           }
            Customer customer = customerRepository.findCustomerByIdAndActive(id,EnumAvailableStatus.ACTIVE.value);
            if (customer == null) {
                throw new BankException(ExceptionConstants.INVALID_REQUEST_DATA,"Invalid request data");
            }
           RespCustomer respCustomer = mapping(customer);
            response.setT(respCustomer);
            response.setStatus(RespStatus.getsuccessMessage());
        }catch (BankException ex){
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex){
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
            ex.printStackTrace();
        }
        return response;
    }

    private RespCustomer mapping (Customer customer) {
        return RespCustomer.builder()
                .id(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .address(customer.getAddress())
                .phone(customer.getPhone())
                .cif(customer.getCif())
                .seria(customer.getSeria())
                .dob(customer.getDob())
                .pin(customer.getPin())
                .build();
    }
    @Override
    public Response createCustomer(ReqCustomer reqCustomer){
        Response response=new Response();
        try {
            String name = reqCustomer.getName();
            String surname = reqCustomer.getSurname();
            if (name == null || surname == null) {
                throw new BankException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Customer customer = Customer.builder()
                    .name(reqCustomer.getName())
                    .surname(reqCustomer.getSurname())
                    .seria(reqCustomer.getSeria())
                    .phone(reqCustomer.getPhone())
                    .pin(reqCustomer.getPin())
                    .address(reqCustomer.getAddress())
                    .dob(reqCustomer.getDob())
                    .cif(reqCustomer.getCif())
                    .build();
            customerRepository.save(customer);
            response.setStatus(RespStatus.getsuccessMessage());
        }catch (BankException ex){
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex){
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response updateCustomer(ReqCustomer reqCustomer){
        Response response = new Response();
        try {
            Long reqCustomerId = reqCustomer.Id();
            String name = reqCustomer.getName();
            String surname = reqCustomer.getSurname();
            if (reqCustomerId == null || name == null || surname == null) {
                throw new BankException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Customer customer = customerRepository.findCustomerByIdAndActive(customerId,EnumAvailableStatus.ACTIVE.value);
            if (customer == null) {
                throw new BankException(ExceptionConstants.CUSTOMER_NOT_FOUND, "customer not found");
            }
                    customer.setName(name);
                    customer.setSurname(surname);
                    customer.setSeria(reqCustomer.getSeria());
                    customer.setPhone(reqCustomer.getPhone());
                    customer.setPin(reqCustomer.getPin());
                    customer.setAddress(reqCustomer.getAddress());
                    customer.setDob(reqCustomer.getDob());
                    customer.setCif(reqCustomer.getCif());
            customerRepository.save(customer);
            response.setStatus(RespStatus.getsuccessMessage());
        }catch (BankException ex){
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex){
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
            ex.printStackTrace();
        }

        return response;
    }
    @Override
    public Response deleteCustomer(Long id){
        Response response = new Response();
        try {
            if(id==null) {
                throw new BankException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Customer customer = customerRepository.findCustomerByIdAndActive(id,EnumAvailableStatus.ACTIVE.value);
            if (customer == null) {
                throw new BankException(ExceptionConstants.CUSTOMER_NOT_FOUND, "customer not found");
            }
            customer.setActive(EnumAvailableStatus.DEACTIVE.value);
            customerRepository.save(customer);
            response.setStatus(RespStatus.getsuccessMessage());


        }catch (BankException ex){
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex){
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
            ex.printStackTrace();
        }
        return response;
    }

}