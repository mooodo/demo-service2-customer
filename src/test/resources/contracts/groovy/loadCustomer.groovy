package contracts.groovy

import org.springframework.cloud.contract.spec.Contract

Contract.make {
	description("load a customer by id.")
	request {
	    method GET()
	    url("/orm/customer/load") {
	        queryParameters {
	            parameter("id","10001")
	        }
	    }
	}
	response {
	    body(file('customer.json'))
	    status 200
	    headers {
			contentType(applicationJson())
		}
	}
}