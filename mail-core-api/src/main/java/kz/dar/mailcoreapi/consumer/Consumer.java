package kz.dar.mailcoreapi.consumer;

import kz.dar.mailcoreapi.config.MessaginConfig;
import kz.dar.mailcoreapi.feign.EmployeeFeign;
import kz.dar.mailcoreapi.mail.EmailServiceImpl;
import kz.dar.mailcoreapi.model.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Consumer {

    @Autowired
    EmailServiceImpl emailService;

    @Qualifier("kz.dar.mailcoreapi.feign.EmployeeFeign")
    @Autowired
    EmployeeFeign employeeFeign;

    @RabbitListener(queues = MessaginConfig.QUEUE)
    public void consumeMessageFromQueue(ClientPaymentDTO clientPaymentDTO) {
        sendMessage(clientPaymentDTO.getClient(), createMessageForClient(clientPaymentDTO));
        List<EmployeeDTO> employeeDTOList = employeeFeign.getEmployeeByDepartment("FINANCE").getBody();
        for (EmployeeDTO employee:
             employeeDTOList) {
            System.out.println(employeeFeign.getEmployeeByDepartment("FINANCE").getBody());
            sendMessage(employee,createMessageForEmployee(clientPaymentDTO,employee));
        }
        System.out.println("Message recieved from queue : " + clientPaymentDTO.toString());
    }
    private void sendMessage(Dispatched dispatched,String message){
        String email = dispatched.getEmail();
        emailService.sendSimpleMessage(email,"Payment",message);
    }


    public String createMessageForClient(ClientPaymentDTO clientPaymentDTO){

        return  "Good day, " + clientPaymentDTO.getClient().getName() + "!\n" +
                "Thank you for paying the utilities at address: " + clientPaymentDTO.getAddress() + "\n" +
                "Total amount of payment is " + clientPaymentDTO.getTotalAmount() + "\n"+
                "Payment includes: " + "\n" +
                createMessageUtilities(clientPaymentDTO.getServicePayments())+
                "Good luck!";

    }

    public String createMessageUtilities(List<ServicePayment> servicePaymentList){
        StringBuffer stringBuffer = new StringBuffer();
        for (ServicePayment servicePayment:
                servicePaymentList) {
            stringBuffer.append("Type of service: " + servicePayment.getServiceType() +" Cost: " + servicePayment.getAmount() + "KZT" +"\n");
        }
        return stringBuffer.toString();
    }

    public String createMessageForEmployee(ClientPaymentDTO clientPaymentDTO, EmployeeDTO employeeDTO){
        return  "Good day, " + employeeDTO.getName() + "!\n" +
                "Client with id " + clientPaymentDTO.getClient().getId() + " and name " + clientPaymentDTO.getClient().getName()+
                " paid the utilities at address: " + clientPaymentDTO.getAddress() + "\n" +
                "Total amount of payment is " + clientPaymentDTO.getTotalAmount() + "\n"+
                "Payment includes: " + "\n" +
                createMessageUtilities(clientPaymentDTO.getServicePayments())+
                "Good luck!";
    }
}