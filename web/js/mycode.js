function ShowNotify() {
    if (document.getElementById('showValue').style.display === 'none') {
        document.getElementById('showValue').style.display = 'block';
    } else {
        document.getElementById('showValue').style.display = 'none';
    }
}

function ShowNotify1() {
    if (document.getElementById('showValue1').style.display === 'none') {
        document.getElementById('showValue1').style.display = 'block';
    } else {
        document.getElementById('showValue1').style.display = 'none';
    }
}

function ShowOrders() {
    if (document.getElementById('orders').style.display === 'none') {
        document.getElementById('orders').style.display = 'block';
    } else {
        document.getElementById('orders').style.display = 'none';
    }
}
function getSelectItemThat(id) {
    for (var i = 1; i <= 3; i++)
    {
        document.getElementById(i).checked = false;
    }
    document.getElementById(id).checked = true;
}
function ShowDateReceipt() {
    if (document.getElementById('showDateReceipt').style.display === 'none') {
        document.getElementById('showDateReceipt').style.display = 'block';
    } else {
        document.getElementById('showDateReceipt').style.display = 'none';
    }
}

function ShowSideBar() {
    if (document.getElementById('side-bar').style.display === 'none') {
        document.getElementById('side-bar').style.display = 'block';
    } else {
        document.getElementById('side-bar').style.display = 'none';
    }
}


function ShowProduct() {
    if (document.getElementById('product').style.display === 'none') {
        document.getElementById('product').style.display = 'block';
    } else {
        document.getElementById('product').style.display = 'none';
    }
}

function ShowDetailOrder() {
    if (document.getElementById('orderDetail').style.display === 'block') {
        document.getElementById('orderDetail').style.display = 'none';
    } else {
        document.getElementById('orderDetail').style.display = 'none';
    }
}

function ShowProductDetail() {
    if (document.getElementById('productDetail').style.display === 'block') {
        document.getElementById('productDetail').style.display = 'none';
    } else {
        document.getElementById('productDetail').style.display = 'none';
    }
}


function ShowSort() {
    if (document.getElementById('sortLabel').style.display === 'none') {
        document.getElementById('sortLabel').style.display = 'block';
    } else {
        document.getElementById('sortLabel').style.display = 'none';
    }
}

function submitForm() {
    document.studentForm.action.value = 'UpdateReceiptDetailVirtual';
}
function SIVTRSearchForm() {
    document.SIVTRSearch.action.value = 'SortInventory';
}
function SDACDDSearchForm() {
    document.SDACDDSearch.action.value = 'SearchOrderAccountDate';
}
function SIACOSearchForm() {
    document.SIACOSearch.action.value = 'SortIssue';
}
function RDSearchForm() {
    document.RDSearch.action.value = 'ShowProductCreate';
}
function updateProductManager() {
    document.RDSearch.action.value = 'updateProductManager';
}
function RDPSearchForm() {
    document.RDPSearch.action.value = 'AddVirtualReceiDetail';
}
function RdSearchForm() {
    document.RdSearch.action.value = 'SearchReceiptDate';
}
function RSearchForm() {
    document.RSearch.action.value = 'SeacrhReceipt';
}
function SRDSearchForm() {
    document.SRDSearch.action.value = 'ShowDetailReceipt';
}
function OSearchForm() {
    document.OSearch.action.value = 'SearchIssue';
}
function SIDSearchForm() {
    document.SIDSearch.action.value = 'ShowDetailOrder';
}
function SIdSearchForm() {
    document.SIdSearch.action.value = 'SearchIssueDate';
}
function OISearchForm() {
    document.SIDSearch.action.value = 'InsertIssue';
}
function OdSearchForm() {
    document.OdSearch.action.value = 'SearchOrderDate';
}
function OOSearchForm() {
    document.OOSearch.action.value = 'SeacrhOrder';
}
function changeExportOrderBtn() {
    document.exportOrder.action.value = 'ExportOrderSeller';
}
function SRSearchForm() {
    document.SRSearch.action.value = 'ShowReport';
}
function showSortReceiptManager() {
    document.SortReceiptManager.action.value = 'sortReceiptManager';
}
function showSearchReceiptManager() {
    document.SearchReceiptManager.action.value = 'searchReceiptManager';
}
function showSortReceipt() {
    document.sortReceipt.action.value = 'SortReceipt';
}
function showSortOrder() {
    document.sortOrder.action.value = 'SortOrder';
}
function showSortInventory(){
        document.sortInventory.action.value = 'SortInventory';
}
function showViewRceiptManager() {
    document.viewRceiptManager.action.value = 'ViewRceiptManager';
}
function showSearchInvenSK() {
    document.searchInvenSK.action.value = 'SearchInvenSK';
}
function showSaveInvenSK() {
    document.saveInvenSK.action.value = 'SaveInvenSK';
}
function showUpdateBinStockepp() {
    document.updateBinStockepp.action.value = 'UpdateBinStockepp';
}
function showCreateBinSK() {
    document.createBinSK.action.value = 'CreateBinSK';
}
function showSaveCreateSK() {
    document.saveCreateSK.action.value = 'SaveCreateSK';
}
function showSortIssueStockeeper() {
    document.sortIssueStockeeper.action.value = 'SortIssueStockeeper';
}
function showSearchIssueStockeeper() {
    document.searchIssueStockeeper.action.value = 'SearchIssueStockeeper';
}
function showSortIssueManager() {
    document.sortIssueManager.action.value = 'SortIssueManager';
}
function showSearchIssueManager() {
    document.searchIssueManager.action.value = 'SearchIssueManager';
}
function showViewIssueDetailManager() {
    document.viewIssueManager.action.value = 'ViewIssueManager';
}
function showProductSearchAC() {
    document.productSearchAC.action.value = 'SearchProductAccount';
}
function showExportInventoryAC() {
    document.exportInventoryAC.action.value = 'ExportInventory';
}
function showSearchProductMN() {
    document.searchProductMN.action.value = 'SearchProductManager';
}
function ShowVeiwProductMN() {
    document.searchProductMN.action.value = 'ViewProductManager';
}
function ShowCreateProductManager() {
    document.createProductManager.action.value = 'CreateProductManager';
}
function SRISearchForm() {
    document.SRISearch.action.value = 'SearchInventoryAlpha';
}
function SRIdSearchForm() {
    document.SRIdSearch.action.value = 'SearchInventory';
}
function CISearchForm() {
    document.CISearch.action.value = 'UpdateIssue';
}
function PRSearchForm() {
    document.PRSearch.action.value = 'ShowProductReprot';
}
function APRSearchForm() {
    document.APRSearch.action.value = 'AddVirtualInventory';
}
function AVRSearchForm() {
    document.AVRSearch.action.value = 'UpdateInventoryVirtua';
}
function ODSSearchForm() {
    document.ODSSearch.action.value = 'SearchOrderSeller';
}
function ODSSSearchForm() {
    document.ODSSSearch.action.value = 'SortOrderSeller';
}
function ODVSSearchForm() {
    document.ODVSSearch.action.value = 'ViewOrderDetailSeller';
}
function SPOSellerForm() {
    document.SPOSeller.action.value = 'InsertOrderDetailSeller';
}
function SPODSellerForm() {
    document.SPODSeller.action.value = 'SearchProductOrderSeller';
}
function AVPSellerForm() {
    document.AVPSeller.action.value = 'AddVirtualOrderSeller';
}
function UVORSellerForm() {
    document.UVORSeller.action.value = 'UpdateOrderVirtualSeller';
}
