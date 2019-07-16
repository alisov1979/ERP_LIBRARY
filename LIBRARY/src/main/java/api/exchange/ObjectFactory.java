
package api.exchange;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the api.exchange package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PropertyFile_QNAME = new QName("http://exchange.mule.ptr/", "file");
    private final static QName _ObjectHashKey_QNAME = new QName("http://exchange.mule.ptr/", "hashKey");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: api.exchange
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetObjectResponse }
     * 
     */
    public GetObjectResponse createGetObjectResponse() {
        return new GetObjectResponse();
    }
    
    public ArrayOfObjects createArrayOfObjects() {
        return new ArrayOfObjects();
    }

    /**
     * Create an instance of {@link Object }
     * 
     */
    public Object createObject() {
        return new Object();
    }

    /**
     * Create an instance of {@link Execute }
     * 
     */
    public Execute createExecute() {
        return new Execute();
    }

    /**
     * Create an instance of {@link GetQueueSizeResponse }
     * 
     */
    public GetQueueSizeResponse createGetQueueSizeResponse() {
        return new GetQueueSizeResponse();
    }

    /**
     * Create an instance of {@link CalculatePrices }
     * 
     */
    public CalculatePrices createCalculatePrices() {
        return new CalculatePrices();
    }

    /**
     * Create an instance of {@link CreatePaymentDocument }
     * 
     */
    public CreatePaymentDocument createCreatePaymentDocument() {
        return new CreatePaymentDocument();
    }

    /**
     * Create an instance of {@link Item }
     * 
     */
    public Item createItem() {
        return new Item();
    }

    /**
     * Create an instance of {@link GetBaseStructureResponse }
     * 
     */
    public GetBaseStructureResponse createGetBaseStructureResponse() {
        return new GetBaseStructureResponse();
    }

    /**
     * Create an instance of {@link SetAndModifyObject }
     * 
     */
    public SetAndModifyObject createSetAndModifyObject() {
        return new SetAndModifyObject();
    }

    /**
     * Create an instance of {@link GetPrintFormResponse }
     * 
     */
    public GetPrintFormResponse createGetPrintFormResponse() {
        return new GetPrintFormResponse();
    }

    /**
     * Create an instance of {@link CompareItemsResponse }
     * 
     */
    public CompareItemsResponse createCompareItemsResponse() {
        return new CompareItemsResponse();
    }

    /**
     * Create an instance of {@link CreatePaymentDocumentFullSum }
     * 
     */
    public CreatePaymentDocumentFullSum createCreatePaymentDocumentFullSum() {
        return new CreatePaymentDocumentFullSum();
    }

    /**
     * Create an instance of {@link GetQueueSize }
     * 
     */
    public GetQueueSize createGetQueueSize() {
        return new GetQueueSize();
    }

    /**
     * Create an instance of {@link Row }
     * 
     */
    public Row createRow() {
        return new Row();
    }

    /**
     * Create an instance of {@link GetMetadataResponse }
     * 
     */
    public GetMetadataResponse createGetMetadataResponse() {
        return new GetMetadataResponse();
    }

    /**
     * Create an instance of {@link ShipOrder }
     * 
     */
    public ShipOrder createShipOrder() {
        return new ShipOrder();
    }

    /**
     * Create an instance of {@link CreateCertificateOrder }
     * 
     */
    public CreateCertificateOrder createCreateCertificateOrder() {
        return new CreateCertificateOrder();
    }

    /**
     * Create an instance of {@link Property }
     * 
     */
    public Property createProperty() {
        return new Property();
    }

    /**
     * Create an instance of {@link CloseOrder }
     * 
     */
    public CloseOrder createCloseOrder() {
        return new CloseOrder();
    }

    /**
     * Create an instance of {@link ConfirmOrderResponse }
     * 
     */
    public ConfirmOrderResponse createConfirmOrderResponse() {
        return new ConfirmOrderResponse();
    }

    /**
     * Create an instance of {@link CreatePaymentDocumentFullSumResponse }
     * 
     */
    public CreatePaymentDocumentFullSumResponse createCreatePaymentDocumentFullSumResponse() {
        return new CreatePaymentDocumentFullSumResponse();
    }

    /**
     * Create an instance of {@link GetObject }
     * 
     */
    public GetObject createGetObject() {
        return new GetObject();
    }

    /**
     * Create an instance of {@link GetPrintForms }
     * 
     */
    public GetPrintForms createGetPrintForms() {
        return new GetPrintForms();
    }

    /**
     * Create an instance of {@link SetObject }
     * 
     */
    public SetObject createSetObject() {
        return new SetObject();
    }

    /**
     * Create an instance of {@link GetObjectTestResponse }
     * 
     */
    public GetObjectTestResponse createGetObjectTestResponse() {
        return new GetObjectTestResponse();
    }

    /**
     * Create an instance of {@link ModifyOrderResponse }
     * 
     */
    public ModifyOrderResponse createModifyOrderResponse() {
        return new ModifyOrderResponse();
    }

    /**
     * Create an instance of {@link SetAndModifyObjectResponse }
     * 
     */
    public SetAndModifyObjectResponse createSetAndModifyObjectResponse() {
        return new SetAndModifyObjectResponse();
    }

    /**
     * Create an instance of {@link CalculatePricesResponse }
     * 
     */
    public CalculatePricesResponse createCalculatePricesResponse() {
        return new CalculatePricesResponse();
    }

    /**
     * Create an instance of {@link ConfirmOrder }
     * 
     */
    public ConfirmOrder createConfirmOrder() {
        return new ConfirmOrder();
    }

    /**
     * Create an instance of {@link CreateCertificateOrderResponse }
     * 
     */
    public CreateCertificateOrderResponse createCreateCertificateOrderResponse() {
        return new CreateCertificateOrderResponse();
    }

    /**
     * Create an instance of {@link ModifyOrder }
     * 
     */
    public ModifyOrder createModifyOrder() {
        return new ModifyOrder();
    }

    /**
     * Create an instance of {@link PrepareOrderProductTableResponse }
     * 
     */
    public PrepareOrderProductTableResponse createPrepareOrderProductTableResponse() {
        return new PrepareOrderProductTableResponse();
    }

    /**
     * Create an instance of {@link SetObjectResponse }
     * 
     */
    public SetObjectResponse createSetObjectResponse() {
        return new SetObjectResponse();
    }

    /**
     * Create an instance of {@link ExecuteResponse }
     * 
     */
    public ExecuteResponse createExecuteResponse() {
        return new ExecuteResponse();
    }

    /**
     * Create an instance of {@link GetPrintForm }
     * 
     */
    public GetPrintForm createGetPrintForm() {
        return new GetPrintForm();
    }

    /**
     * Create an instance of {@link GetBaseStructure }
     * 
     */
    public GetBaseStructure createGetBaseStructure() {
        return new GetBaseStructure();
    }

    /**
     * Create an instance of {@link GetPrintFormsResponse }
     * 
     */
    public GetPrintFormsResponse createGetPrintFormsResponse() {
        return new GetPrintFormsResponse();
    }

    /**
     * Create an instance of {@link CompareItems }
     * 
     */
    public CompareItems createCompareItems() {
        return new CompareItems();
    }

    /**
     * Create an instance of {@link GetObjectTest }
     * 
     */
    public GetObjectTest createGetObjectTest() {
        return new GetObjectTest();
    }

    /**
     * Create an instance of {@link ShipOrderResponse }
     * 
     */
    public ShipOrderResponse createShipOrderResponse() {
        return new ShipOrderResponse();
    }

    /**
     * Create an instance of {@link GetMetadata }
     * 
     */
    public GetMetadata createGetMetadata() {
        return new GetMetadata();
    }

    /**
     * Create an instance of {@link PrepareOrderProductTable }
     * 
     */
    public PrepareOrderProductTable createPrepareOrderProductTable() {
        return new PrepareOrderProductTable();
    }

    /**
     * Create an instance of {@link GetData }
     * 
     */
    public GetData createGetData() {
        return new GetData();
    }

    /**
     * Create an instance of {@link GetDataResponse }
     * 
     */
    public GetDataResponse createGetDataResponse() {
        return new GetDataResponse();
    }

    /**
     * Create an instance of {@link CloseOrderResponse }
     * 
     */
    public CloseOrderResponse createCloseOrderResponse() {
        return new CloseOrderResponse();
    }

    /**
     * Create an instance of {@link CreatePaymentDocumentResponse }
     * 
     */
    public CreatePaymentDocumentResponse createCreatePaymentDocumentResponse() {
        return new CreatePaymentDocumentResponse();
    }

    /**
     * Create an instance of {@link Complextype }
     * 
     */
    public Complextype createComplextype() {
        return new Complextype();
    }

    /**
     * Create an instance of {@link Simpletype }
     * 
     */
    public Simpletype createSimpletype() {
        return new Simpletype();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://exchange.mule.ptr/", name = "file", scope = Property.class)
    public JAXBElement<byte[]> createPropertyFile(byte[] value) {
        return new JAXBElement<byte[]>(_PropertyFile_QNAME, byte[].class, Property.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://exchange.mule.ptr/", name = "hashKey", scope = Object.class)
    public JAXBElement<String> createObjectHashKey(String value) {
        return new JAXBElement<String>(_ObjectHashKey_QNAME, String.class, Object.class, value);
    }

}
