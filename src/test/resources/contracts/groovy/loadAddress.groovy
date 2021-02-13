package contracts.groovy

import org.springframework.cloud.contract.spec.Contract

Contract.make {
	description("load an address by id.")
	request {
	    method GET()
	    url("/orm/customer/loadAddress") {
	        queryParameters {
	            parameter("id","1000100")
	        }
	    }
	}
	response {
	    body(file('address.json'))
	    status 200
	    headers {
			contentType(applicationJson())
		}
	}
}