SHELL = /bin/bash

VERSION ?= $(shell git rev-parse --short=7 HEAD)
IMAGE ?= paulosalonso/zup
ROOTPATH ?= $(shell git rev-parse --show-toplevel)
TMP_DIR := "$(shell mktemp -d $(ROOTPATH)/tmp.build.XXXXXXXXXX)"
ARTIFACTS ?= "$(shell git rev-parse --show-toplevel)/target/zup.jar"
DOCKERPATH ?= "$(shell git rev-parse --show-toplevel)/docker"
BRANCH ?= $(shell git name-rev --name-only HEAD)

# Define here the list of images that should be builded
LIST_OF_IMAGES_TO_BUILD := $(IMAGE)
export

# ensure that latest isn't use for build
define isLatest
	@[ $(VERSION) != "latest" ] || (echo "VERSION can't be latest in $(@)"; exit 1)
endef

# Rule "build:local"
.PHONY: build\:local
build\:local: ## Build the Dockerfile.local
	$(call isLatest)
	cp -R $(ARTIFACTS) $(TMP_DIR)
	cp -R $(DOCKERPATH)/* $(TMP_DIR)
	cd $(TMP_DIR)
	docker build --force-rm \
		-t "$(IMAGE)":local \
		--build-arg CURRENT_BRANCH=$(BRANCH) \
		$(TMP_DIR)
	sleep 0.2
	rm -rf $(TMP_DIR)
