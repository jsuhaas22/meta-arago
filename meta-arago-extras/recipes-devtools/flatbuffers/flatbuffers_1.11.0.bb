SUMMARY = "Memory Efficient Serialization Library"
HOMEPAGE = "https://github.com/google/flatbuffers"
SECTION = "console/tools"
LICENSE = "Apache-2.0"

PACKAGE_BEFORE_PN = "${PN}-compiler"

RDEPENDS:${PN}-compiler = "${PN}"
RDEPENDS:${PN}-dev += "${PN}-compiler"

LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=a873c5645c184d51e0f9b34e1d7cf559"

SRCREV = "bf9eb67ab9371755c6bcece13cadc7693bcbf264"

SRC_URI = "git://github.com/google/flatbuffers.git;protocol=https;branch=master"

# Make sure C++11 is used, required for example for GCC 4.9
CXXFLAGS += "-std=c++11"
BUILD_CXXFLAGS += "-std=c++11"

# BUILD_TYPE=Release is required, otherwise flatc is not installed
EXTRA_OECMAKE += "\
    -DCMAKE_BUILD_TYPE=Release \
    -DFLATBUFFERS_BUILD_TESTS=OFF \    
    -DFLATBUFFERS_BUILD_SHAREDLIB=ON \
"

inherit cmake

S = "${WORKDIR}/git"

FILES:${PN}-compiler = "${bindir}"

BBCLASSEXTEND = "native nativesdk"
