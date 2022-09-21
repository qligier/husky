# CARA custom build of the Husky library

⚠️This is a custom build of the Husky library for CARA/HUG. Please don't use it, things break really often and all
changes are to be integrated back in Husky when it's stable.

This convenience API for IHE transactions and CDA documents allows easy access and conformity to eHealth affinity
domains. The project is based on a country independent architecture, but implements also Swiss extensions. Other country
extensions are possible, your contributions are welcome.

The latest Javadoc is published on <a href="https://project-husky.github.io/husky/">project-husky.github.io/husky</a> and the documentation is available in the [wiki](https://github.com/project-husky/husky/wiki).

## Installation

- **[Install Guide](docs/Installation.md)**

## Development

- **[Development Guidelines](docs/Development.md)**

## Implemented IHE Profiles

Husky offers support for multiple IHE ITI profiles. For each profile transaction there is a convenience method.
Supported profiles are

| Profile             | Transaction          | Description                              |
| ------------------- | -------------------- | ---------------------------------------- |
| [PDQ](docs/PDQV3.md)  | ITI-47               | Query patient demographics               |
| [PIX](docs/PIXV3.md)  | ITI-44, ITI-45       | Patient identity transactions            |
| [PPQ](docs/PPQ.md)  | PPQ-1, PPQ-2         | Privacy Policy Queries                   |
| [SVS](docs/SVS.md)  | ITI-48               | Retrieve value set                       |
| [XDM](docs/XDM.md)  | ITI-32               | Exchange of documents via standard media |
| [XDS](docs/XDS.md)  | ITI-18,ITI-41,ITI-43 | Exchange of documents                    |
| [XUA](docs/XUA.md)  | ITI-40               | Provide identity assertions              |

## Modules

* `husky-api`: The main module managing dependencies.
    * `husky-common`: The common parent module.
        * `husky-common-gen`: The common part that are generic.
        * `husky-common-ch`: The common part dedicated to the Swiss environment.
    * `husky-communication`: Implementation of transactions with [IPF](https://github.com/oehf/ipf)
      and [Apache Camel](https://github.com/apache/camel).
        * `husky-communication-gen`: Generic transactions.
        * [`husky-communication-ch`](https://github.com/project-husky/husky/wiki/Module:-husky-communication-ch): Swiss-specific transactions.
        * `husky-communication-ppq`: CH:PPQ (Privacy Policy Query) transactions.
        * `husky-communication-valueset-gen`: Models and client to retrieve value sets from ArtDecor (please note that
          Swiss value sets are usually transformed into enums and put in other modules).
        * `husky-communication-xua`: Models, generator, parser and validator of CH-EPR SAML2 Assertions (per IHE XUA
          profile).
            * `husky-xua-ch-impl`:
            * `husky-xua-gen-api`:
            * `husky-xua-gen-impl`:
    * `husky-emed`: The eMedication parent module.
        * `husky-emed-ch-common`: Common parts for the eMedication modules.
        * `husky-emed-ch-cda`: Models, digesters and aggregators of CDA-CH-EMED documents.
        * `husky-emed-ch-cda-narrative`: Generator of narrative text for CDA-CH-EMED documents, in StrucDocText or HTML/PDF
          with customizable templates.
        * [`husky-emed-ch-cda-validation`](https://github.com/project-husky/husky/wiki/Module:-husky-emed-ch-cda-validation): A validator of CDA-CH-EMED documents with the IHE Pharm XML Schema, the CDA-CH-EMED
          Schematrons and the PDF/A validator.
        * `husky-emed-ch-conversion`: Not implemented yet. A conveniance API to convert documents between CDA-CH-EMED and
          CH-EMED specifications.
    * `husky-fhir`: The FHIR parent module.
        * `husky-fhir-communication`: Helper for FHIR communication.
        * `husky-fhir-structures`:
            * `husky-fhir-structures-gen`: Generic FHIR structures.
            * `husky-fhir-structures-ch`: Swiss FHIR structures.
    * `husky-validation`: The validation parent module.
        * [`husky-validation-service`](https://github.com/project-husky/husky/wiki/Module:-husky-validation-service): The module implements services to validate XML documents with XML Schemas and
          Schematron, as well as PDF documents for the A-1 and A-2 conformance levels.

## License

This code is made available under the terms of the Eclipse Public License v1.0 in the
[github project](https://github.com/project-husky/husky). There, you also find a list of the contributors and the
license information. This project has been developed further and modified by the joined working group Husky on the basis
of the eHealth Connector opensource project from June 28, 2021, whereas medshare GmbH is the initial and main
contributor/author of the eHealth Connector. The final version of the eHealthConnector is available as a release in this
project.

All other accompanying materials are made available under the terms of the Creative Commons license
Attribution-ShareAlike 3.0 Switzerland (CC BY-SA 3.0 CH)
see https://creativecommons.org/licenses/by-sa/3.0/ch/ (Ausschliesslicher Gerichtsstand ist Bern, Schweiz). The
exclusive place of jurisdiction is Bern, Switzerland for the Creative Commons license.
