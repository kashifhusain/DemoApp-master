account.soapservice.url.host=ec2-54-235-234-14.compute-1.amazonaws.com
account.soapservice.url.port=8080
account.soapservice.url.suffix=accountservice
account.soapservice.url.wsdl=/accountservice.wsdl

catalog.service.url.host=ec2-54-235-234-14.compute-1.amazonaws.com
catalog.service.url.port=8080
catalog.service.url.suffix=catalog/api/v1

order.service.url.host=ec2-54-235-234-14.compute-1.amazonaws.com
order.service.url.port=8080
order.service.url.suffix=order/api/v1

mastercredit.service.url.host=ec2-54-235-234-14.compute-1.amazonaws.com
mastercredit.service.url.port=8080
mastercredit.service.url.suffix=MasterCredit/api/v1

shipex.soapservice.url.host=ec2-54-235-234-14.compute-1.amazonaws.com
shipex.soapservice.url.port=8080
shipex.soapservice.url.suffix=ShipEx
shipex.soapservice.url.wsdl=/shipex.wsdl

safepay.service.url.host=ec2-54-235-234-14.compute-1.amazonaws.com
safepay.service.url.port=8080
safepay.service.url.suffix=SafePay/api/v1

#example order.hibernate.db.url=jdbc:postgresql://localhost:5432/adv-root?loglevel=0
#example order.service.url=localhost:8080/order

