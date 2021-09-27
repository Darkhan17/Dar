package kz.dar.serviceapi.model;

import org.springframework.data.domain.Page;

import java.util.List;

public class ResponseList {
    private int numberOfPages;
    private long totalElements;
    private int elemetsOnPage;
    private List<ClientPaymentViewModel> clientPaymentViewModelList;


    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getElemetsOnPage() {
        return elemetsOnPage;
    }

    public void setElemetsOnPage(int elemetsOnPage) {
        this.elemetsOnPage = elemetsOnPage;
    }

    public ResponseList(Page<ClientPaymentViewModel> page){
        numberOfPages = page.getTotalPages();
        clientPaymentViewModelList = page.getContent();
        totalElements = page.getTotalElements();
        elemetsOnPage = clientPaymentViewModelList.size();
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public List<ClientPaymentViewModel> getClientPaymentViewModelList() {
        return clientPaymentViewModelList;
    }

    public void setClientPaymentViewModelList(List<ClientPaymentViewModel> clientPaymentViewModelList) {
        this.clientPaymentViewModelList = clientPaymentViewModelList;
    }
}
