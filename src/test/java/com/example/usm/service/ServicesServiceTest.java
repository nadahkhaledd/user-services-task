package com.example.usm.service;

import com.example.usm.entity.Service;
import com.example.usm.enums.ServiceStatus;
import com.example.usm.exception.service.ServiceNotFoundException;
import com.example.usm.repository.ServiceRepository;
import com.example.usm.service.services.IServicesService;
import com.example.usm.service.services.ServicesService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ServicesServiceTest {
    
    private IServicesService servicesService;
    private ServiceRepository serviceRepositoryMock;
    private Service service;

    public ServicesServiceTest(){
        serviceRepositoryMock = Mockito.mock(ServiceRepository.class);
        servicesService = new ServicesService(serviceRepositoryMock);
    }

    public Service initializeService(){
        Service service = null;
        try {
            service =  new Service(1, "vodafone",
                    new SimpleDateFormat("dd-MM-yyyy").parse("22-10-2022"),
                    ServiceStatus.Active);
        }
        catch (ParseException e){
            System.out.println(e.getMessage());
        }
        return service;
    }

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.service = initializeService();
    }

    @Test(expected = NullPointerException.class)
    public void testAdd_sendNullEntity_returnNullPointerException() {
        /*ACT*/
        servicesService.add(null);
    }

    @Test
    public void  testAdd_SendValidData_ExpectReturnService(){
        // Arrange
        when(serviceRepositoryMock.save(any(Service.class))).thenReturn(service);
        // ACT
        Service returnedService = servicesService.add(service);

        // Assert
        assertEquals(service, returnedService);
        verify(serviceRepositoryMock, times(1)).save(any(Service
                .class));
    }

    @Test
    public void testGetAll_ExpectEmptyList(){
        // Arrange
        when(serviceRepositoryMock.findAll()).thenReturn(new ArrayList<>());

        // ACT
        ArrayList<Service> services = (ArrayList<Service>) servicesService.getAll();

        // Assert
        assertEquals(0, services.size());
    }

    @Test
    public void testGetAll_SendServiceEntity_ExpectOneResult(){
        // Arrange
        when(serviceRepositoryMock.findAll()).thenReturn(new ArrayList<>(Arrays.asList(service)));

        // ACT
        ArrayList<Service> services = (ArrayList<Service>) servicesService.getAll();

        // Assert
        assertEquals(1, services.size());
        assertEquals("vodafone", services.get(0).getVendor());
    }

    @Test(expected = ServiceNotFoundException.class)
    public void testFindByUID_sendInvalidUID_ExpectServiceNotFoundException(){
        // Arrange
        when(serviceRepositoryMock.findById(anyInt())).thenReturn(Optional.empty());

        //ACT
        servicesService.findByUID(anyInt());
    }

    @Test
    public void testFindByUID_SendValidUID_ExpectReturnService(){
        //Arrange
        when(serviceRepositoryMock.findById(anyInt())).thenReturn(Optional.of(service));

        //ACT
        Service returned = servicesService.findByUID(service.getUid());

        // Assert
        assertEquals(service, returned);
        verify(serviceRepositoryMock, times(1)).findById(anyInt());
    }

    @Test
    public void testFindByVendor_SendNotFoundVendor_ExpectEmptyList(){
        // Arrange
        when(serviceRepositoryMock.findByVendor(anyString())).thenReturn(new ArrayList<>());

        // Act
        List<Service> services = servicesService.findByVendor(anyString());

        //Assert
        assertEquals(0, services.size());
    }

    @Test
    public void testFindByVendor_SendFoundVendor_ExpectOneResult(){
        // Arrange
        when(serviceRepositoryMock.findByVendor(anyString())).thenReturn(new ArrayList<>(Arrays.asList(service)));

        // Act
        List<Service> services = servicesService.findByVendor(service.getVendor());

        //Assert
        assertEquals(1, services.size());
        assertEquals(ServiceStatus.Active, services.get(0).getStatus());

    }


    @Test
    public void testFindByStatus_SendNotFoundStatus_ExpectEmptyList(){
        // Arrange
        when(serviceRepositoryMock.findByStatus(any(ServiceStatus.class))).thenReturn(new ArrayList<>());

        // Act
        List<Service> services = servicesService.findByStatus(any(ServiceStatus.class));

        //Assert
        assertEquals(0, services.size());
    }

    @Test
    public void testFindByStatus_SendValidData_ExpectOneResult(){
        // Arrange
        when(serviceRepositoryMock.findByStatus(any(ServiceStatus.class))).thenReturn(new ArrayList<>(Arrays.asList(service)));

        // Act
        List<Service> services = servicesService.findByStatus(service.getStatus());

        //Assert
        assertEquals(1, services.size());
        assertEquals(ServiceStatus.Active, services.get(0).getStatus());

    }


    @Test
    public void testFindByVendorAndStatus_SendNotValidData_ExpectEmptyList(){
        // Arrange
        when(serviceRepositoryMock.findByVendorAndStatus(anyString(), any(ServiceStatus.class))).thenReturn(new ArrayList<>());

        // Act
        List<Service> services = servicesService.findByVendorAndStatus(anyString(), any(ServiceStatus.class));

        //Assert
        assertEquals(0, services.size());
    }

    @Test
    public void testFindByVendorAndStatus_SendValidStatusAndInvalidVendor_ExpectEmptyList(){
        // Arrange
        when(serviceRepositoryMock.findByVendorAndStatus(anyString(), any(ServiceStatus.class))).thenReturn(new ArrayList<>());

        // Act
        List<Service> services = servicesService.findByVendorAndStatus("orange", service.getStatus());

        //Assert
        assertEquals(0, services.size());
    }

    @Test
    public void testFindByVendorAndStatus_SendValidData_ExpectOneResult(){
        // Arrange
        when(serviceRepositoryMock.findByVendorAndStatus(anyString(), any(ServiceStatus.class))).thenReturn(new ArrayList<>(Arrays.asList(service)));

        // Act
        List<Service> services = servicesService.findByVendorAndStatus(service.getVendor(), service.getStatus());

        //Assert
        assertEquals(1, services.size());
    }


    
    
    
}
